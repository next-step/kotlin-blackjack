package blackjack.domain.card

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    companion object {
        fun allShuffled(): Deck =
            Suit.entries.flatMap {
                allRankInSuit(it)
            }.shuffled().let(Deck::from)

        private fun allRankInSuit(suit: Suit) =
            Rank.entries.map { Card(suit, it) }
    }
}
