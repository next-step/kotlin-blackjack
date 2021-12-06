package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

@JvmInline
value class Score private constructor(val score: Int) {

    fun isBust(): Boolean = score > BLACK_JACK_SCORE

    operator fun plus(other: Score) = from(score + other.score)

    operator fun compareTo(other: Score) = score.compareTo(other.score)

    companion object {
        private const val MIN_ACE_SCORE = 1
        private const val MAX_ACE_SCORE = 11

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
