package domain

enum class CardNumber(val score: Score) {
    TWO(Score(2, null)),
    THREE(Score(3, null)),
    FOUR(Score(4, null)),
    FIVE(Score(5, null)),
    SIX(Score(6, null)),
    SEVEN(Score(7, null)),
    EIGHT(Score(8, null)),
    NINE(Score(9, null)),
    TEN(Score(10, null)),
    KING(Score(10, null)),
    QUEEN(Score(10, null)),
    JACK(Score(10, null)),
    ACE(Score(1, 10));
}
