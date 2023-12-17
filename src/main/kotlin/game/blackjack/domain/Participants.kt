package game.blackjack.domain

class Participants(private val participants: List<Participant>) {

    fun distributeInitialCards(deck: Deck) = participants.forEach { it.drawCards(deck.initialDraw()) }

    fun drawAdditionalCards(
        deck: Deck,
        drawDecision: (Participant) -> Boolean,
        result: (Participant) -> Unit
    ) = participants.forEach {
        while (it.isNotBust() && drawDecision(it)) {
            it.drawCard(deck.drawOnce())
            result(it)
        }
    }

    fun toNames() = participants.joinToString(", ") { it.name }

    fun toFinalResult() = participants.joinToString("\n") { it.toString() + " - 결과: ${it.getScore()}" }

    override fun toString() = participants.joinToString("\n") { it.toString() }
}
