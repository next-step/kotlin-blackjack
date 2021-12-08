package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

@JvmInline
value class Score private constructor(val score: Int) {

    init {
        require(score >= 0) { SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE }
    }

    fun isBust(): Boolean = score > BLACK_JACK_SCORE

    operator fun plus(other: Score) = from(score + other.score)

    operator fun compareTo(other: Score) = score.compareTo(other.score)

    companion object {
        private const val MIN_ACE_SCORE = 1
        private const val MAX_ACE_SCORE = 11
        private const val SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE = "점수는 0이상이어야 합니다."

        fun getAceScore(totalScore: Score): Score =
            if (from(MAX_ACE_SCORE) + totalScore > from(BLACK_JACK_SCORE)) {
                from(MIN_ACE_SCORE)
            } else {
                from(MAX_ACE_SCORE)
            }

        fun from(score: Int): Score {
            return Score(score)
        }
    }
}
