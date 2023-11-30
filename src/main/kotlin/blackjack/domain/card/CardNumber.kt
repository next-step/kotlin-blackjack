package blackjack.domain.card

import blackjack.domain.Score

enum class CardNumber(
    val score: Score
) {
    ACE(Score(1)),
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

    fun isAce(): Boolean {
        return this == ACE
    }
}
