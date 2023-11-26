package blackjack.domain

data class Card(
    val denomination: Denomination,
    val suit: Suit
) {
    override fun toString(): String {
        return denomination.symbol + suit.symbol
    }
}
