package blackjack.domain

enum class Denomination(private val primaryScore: Score, private val secondaryScore: Score) {

    ACE(secondaryScore = Score(1), primaryScore = Score(11)),
    TWO(Score(2)),
    THREE(Score(3)),
    FOUR(Score(4)),
    FIVE(Score(5)),
    SIX(Score(6)),
    SEVEN(Score(7)),
    EIGHT(Score(8)),
    NINE(Score(9)),
    TEN(Score(10)),
    JACK(Score(10)),
    QUEEN(Score(10)),
    KING(Score(10));

    constructor(score: Score) : this(score, score)
}
