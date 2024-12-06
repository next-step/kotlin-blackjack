package blackjack.entity

class Players(
    val players: List<Player>,
) : Iterable<Player> {
    fun initializeHands(deck: Deck) {
        players.forEach { player ->
            repeat(2) { player.receiveCard(deck.deal()) }
        }
    }

    fun calculateResult(dealer: Dealer): List<GameResult> = players.map { it.calculateResult(dealer) }

    override fun iterator(): Iterator<Player> = players.iterator()
}
