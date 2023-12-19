package game.blackjack.v1.domain

class Participants(private val dealer: Dealer, private val players: List<Participant>) {

    fun distributeInitialCards(deck: Deck) {
        dealer.drawCards(deck.initialDraw())
        players.forEach { it.drawCards(deck.initialDraw()) }
    }

    fun drawAdditionalCards(
        deck: Deck,
        drawDecision: (Participant) -> Boolean,
        result: (Participant) -> Unit
    ) = players.forEach {
        while (it.isNotBust() && drawDecision(it)) {
            it.drawCard(deck.drawOnce())
            result(it)
        }
    }

    fun toPlayerNames() = players.joinToString(", ") { it.name }

    fun toPlayerFinalResults() = players.joinToString("\n") { it.toString() + " - 결과: ${it.getScore()}" }
    
    override fun toString() = players.joinToString("\n") { it.toString() }
}
