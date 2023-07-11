package step2.domain

data class Card(
    val suit: Suit,
    val denomination: Denomination,
) {

    fun display(): String {
        return "${denomination.cardNumber}${suit.suit}"
    }
}
