package blackjack.domain

enum class Denomination(val score: Score, val shape: String) {
    ACE(Score.of(1), "A"),
    TWO(Score.of(2), "2"),
    THREE(Score.of(3), "3"),
    FOUR(Score.of(4), "4"),
    FIVE(Score.of(5), "5"),
    SIX(Score.of(6), "6"),
    SEVEN(Score.of(7), "7"),
    EIGHT(Score.of(8), "8"),
    NINE(Score.of(9), "9"),
    TEN(Score.of(10), "10"),
    JACK(Score.of(10), "10"),
    QUEEN(Score.of(10), "10"),
    KING(Score.of(10), "10");
}
