package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    val value: Int
        get() = rank.value

    override fun toString(): String {
        return "${rank.value}${suit.description}"
    }
}
