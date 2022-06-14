package blackjack.domain.card

data class Card(
    val denomination: Denomination,
    private val suit: Suit
) {
    val defaultPoint: Point = Point(denomination.minValue)

    override fun toString(): String {
        return "${denomination.displayedName}${suit.type}"
    }
}

enum class Suit(
    val type: String
) {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아")
}

enum class Denomination(
    val minValue: Int,
    val maxValue: Int,
    val displayedName: String
) {
    ACE(1, 11, "A"),
    TWO(2, 2, "2"),
    THREE(3, 3, "3"),
    FOUR(4, 4, "4"),
    FIVE(5, 5, "5"),
    SIX(6, 6, "6"),
    SEVEN(7, 7, "7"),
    EIGHT(8, 8, "8"),
    NINE(9, 9, "9"),
    TEN(10, 10, "10"),
    JACK(10, 10, "J"),
    QUEEN(10, 10, "Q"),
    KING(10, 10, "K");
}
