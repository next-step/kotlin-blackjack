package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    val value: Int
        get() = rank.value
}
