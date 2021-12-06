package blackjack.domain

enum class Denomination(val score: Score) {
    ACE(Score.of(1)),
    TWO(Score.of(2)),
    THREE(Score.of(3)),
    FOUR(Score.of(4)),
    FIVE(Score.of(5)),
    SIX(Score.of(6)),
    SEVEN(Score.of(7)),
    EIGHT(Score.of(8)),
    NINE(Score.of(9)),
    TEN(Score.of(10)),
    JACK(Score.of(10)),
    QUEEN(Score.of(10)),
    KING(Score.of(10)),
    ACE_ELEVEN(Score.of(11));
}
