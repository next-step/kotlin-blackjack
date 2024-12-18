package blackjack.domain

data class Card(val rank: Rank, val suit: Suit) {
    companion object {
        val ALL_CARDS: List<Card> =
            Rank.entries.flatMap { rank ->
                Suit.entries.map { suit ->
                    Card(rank, suit)
                }
            }
    }
}
