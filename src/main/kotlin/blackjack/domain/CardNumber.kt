package blackjack.domain

enum class CardNumber(val value: Number, val named: String = "number") {
    ACE(1, "ace"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(10, "jack"),
    QUEEN(10, "queen"),
    KING(10, "king"),
}
