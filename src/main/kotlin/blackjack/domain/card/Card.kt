package blackjack.domain.card

data class Card(
    val suit: CardSuit,
    val rank: CardRank,
) {
    companion object {
        private val cache = mutableListOf<Card>()

        fun of(suit: CardSuit, rank: CardRank): Card {
            val card = cache.find { it.suit == suit && it.rank == rank }
            return card ?: Card(suit, rank).also { cache.add(it) }
        }
    }
}
