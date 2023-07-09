package blackjack.domain

enum class Denomination(val score: Int, val label: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(1, "10"),
    KING(1, "K"),
    QUEEN(1, "Q"),
    JACK(1, "J")
}
