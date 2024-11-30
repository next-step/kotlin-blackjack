package blackjack.domain

class Card(
    val suit: Suit,
    val rank: Rank,
) {
    val rankValue: Int
        get() = rank.value
}
