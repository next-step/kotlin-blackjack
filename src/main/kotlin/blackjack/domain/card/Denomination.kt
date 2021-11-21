package blackjack.domain.card

enum class Denomination(val score: Score) {
    ACE(Score.from(1)),
    TWO(Score.from(2)),
    THREE(Score.from(3)),
    FOUR(Score.from(4)),
    FIVE(Score.from(5)),
    SIX(Score.from(6)),
    SEVEN(Score.from(7)),
    EIGHT(Score.from(8)),
    NINE(Score.from(9)),
    TEN(Score.from(10)),
    JACK(Score.from(10)),
    QUEEN(Score.from(10)),
    KING(Score.from(10));
}
