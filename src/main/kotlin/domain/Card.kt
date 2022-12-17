package domain


class Card(
    val shape: CardShape,
    val number: CardNumber
)

enum class CardNumber(
    val primaryValue: Int,
    val secondaryValue: Int = primaryValue
) {
    ACE(1, 11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10)
}

enum class CardShape {
    HEART, SPADE, CLOVER, DIAMOND
}