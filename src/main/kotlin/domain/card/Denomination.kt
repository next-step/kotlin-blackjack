package domain.card

enum class Denomination(val numbers: Set<Int>, val text: String) {
    ACE(setOf(1, 11), "A"),
    TWO(setOf(2), "2"),
    THREE(setOf(3), "3"),
    FOUR(setOf(4), "4"),
    FIVE(setOf(5), "5"),
    SIX(setOf(6), "6"),
    SEVEN(setOf(7), "7"),
    EIGHT(setOf(8), "8"),
    NINE(setOf(9), "9"),
    TEN(setOf(10), "10"),
    KING(setOf(10), "K"),
    QUEEN(setOf(10), "Q"),
    JACK(setOf(10), "J"),
}
