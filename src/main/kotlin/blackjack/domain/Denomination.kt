package blackjack.domain

enum class Denomination(val score: Int, val initial: String = score.toString()) {
    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    KING(10, "K"),
    QUEEN(10, "Q");
}
