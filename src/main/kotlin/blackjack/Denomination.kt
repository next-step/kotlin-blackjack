package blackjack

enum class Denomination(val scores: Scores) {
    ACE(Scores(listOf(Score(1), Score(11)))),
    TWO(Scores.from(2)),
    THREE(Scores.from(3)),
    FOUR(Scores.from(4)),
    FIVE((Scores.from(5))),
    SIX(Scores.from(6)),
    SEVEN(Scores.from(7)),
    EIGHT(Scores.from(8)),
    NINE(Scores.from(9)),
    TEN(Scores.from(10)),
    JACK(Scores.from(10)),
    QUEEN((Scores.from(10))),
    KING(Scores.from(10)),
}
