package blackjack.domain

class BlackJackManager(
    private val players: Players
) {

    fun giveInitialCards(cardDeck: CardDeck) {
        repeat(INITIAL_CARD_NUM) {
            players.eachAcceptCards(cardDeck)
        }
    }

    fun hitPlayer(question: (Player) -> String, cardDeck: CardDeck, printResult: ((Player) -> Unit)? = null) {
        players.forEach { player ->
            while (player.canHit() && question(player) == "y") {
                player.hit(cardDeck.next())
                printResult?.invoke(player)
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_NUM = 2
    }
}
