package blackjack.domain.card

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    companion object {
        fun allShuffled(): CardDeck =
            Suit.entries.flatMap {
                allRankInSuit(it)
            }.shuffled().let(::CardDeck)

        private fun allRankInSuit(suit: Suit) =
            Rank.entries.map { Card(suit, it) }
    }
}
