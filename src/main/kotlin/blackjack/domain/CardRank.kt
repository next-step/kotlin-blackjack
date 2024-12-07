package blackjack.domain

enum class CardRank(private val score: Score = Score.ZERO) {
    ACE {
        override fun calculateScore(currentScore: Score): Score {
            return if ((currentScore + ACE_SOFT_SCORE) > BUST_SCORE) (currentScore + ACE_HARD_SCORE) else (currentScore + ACE_SOFT_SCORE)
        }
    },
    JACK(score = Score(10)),
    QUEEN(score = Score(10)),
    KING(score = Score(10)),
    TWO(score = Score(2)),
    THREE(score = Score(3)),
    FOUR(score = Score(4)),
    FIVE(score = Score(5)),
    SIX(score = Score(6)),
    SEVEN(score = Score(7)),
    EIGHT(score = Score(8)),
    NINE(score = Score(9)),
    ;

    open fun calculateScore(currentScore: Score): Score {
        return currentScore + score
    }

    companion object {
        val ACE_SOFT_SCORE = Score(11)
        val ACE_HARD_SCORE = Score(1)
    }
}
