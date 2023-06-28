package blackjack.card

enum class CardNumber(val minValue: Int, val maxValue: Int, val displayName: String) {
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
    KING(10, 10, "K"),
    QUEEN(10, 10, "Q"),
    JACK(10, 10, "J")
}
