package blackjack.dto

data class Card(
    val suit: Suit,
    val number: Number
) {
    fun cardName() = "${number.symbol}${suit.symbol}"
}
