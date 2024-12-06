package blackjack.entity

class Players(
    val players: List<Player>,
) : Iterable<Player> {
    fun initializeHands(deck: Deck) {
        players.forEach { player ->
            repeat(2) { player.receiveCard(deck.deal()) }
        }
    }

    fun calculateResult(dealerScore: Int): List<GameResult> = players.map { it.calculateResult(dealerScore) }

    override fun iterator(): Iterator<Player> = players.iterator()
}
