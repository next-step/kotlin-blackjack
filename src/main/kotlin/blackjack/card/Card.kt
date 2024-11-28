package blackjack.card

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    companion object {
        fun random(): Card = Card(rank = Rank.entries.random(), Suit.entries.random())
    }
}
