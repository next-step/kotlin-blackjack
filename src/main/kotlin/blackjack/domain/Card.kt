package blackjack.domain

class Card(
    val suit: Suit,
    val rank: Rank,
) {
    override fun toString(): String = "${suit.displayName}${rank.displayName}"
}
