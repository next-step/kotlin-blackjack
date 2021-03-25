package blackjack.domain.card

enum class Denomination(private val score: Score) {
    ACE(Score.of(11)),
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
    KING(Score.of(10));

    fun calculateScore(otherScore: Score): Score {
        return otherScore +
            when (this) {
                ACE ->
                    if (score + otherScore > Cards.BLACKJACK_SCORE)
                        ACE_OTHER_SCORE
                    else
                        score
                else -> score
            }
    }

    companion object {
        private val ACE_OTHER_SCORE = Score.of(1)
    }
}
