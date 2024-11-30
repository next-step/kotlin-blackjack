package blackjack.card

data class Card(
    val rank: Rank,
    val suit: Suit,
) {
    constructor(rank: Rank) : this(rank, suit = Suit.SPADE)

    companion object {
        fun random(): Card = Card(rank = Rank.entries.random(), Suit.entries.random())
    }
}
