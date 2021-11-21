package blackjack.domain.card

import blackjack.error.ScoreOutOfBoundsException

@JvmInline
value class Score private constructor(val score: Int) {

    fun canPlusExtraAceScore(): Boolean = ((score + EXTRA_ACE_SCORE_RANGE) <= MAXIMUM_SCORE)

    fun isOverMaximum(): Boolean = (score > MAXIMUM_SCORE)

    operator fun plus(other: Score): Score = from(score + other.score)

    companion object {
        private const val MINIMUM_SCORE = 0
        private const val MAXIMUM_SCORE = 21
        private const val EXTRA_ACE_SCORE_RANGE = 10
        private const val CACHE_LIMIT_SCORE = 32
        private val CACHE: Map<Int, Score> = (MINIMUM_SCORE..CACHE_LIMIT_SCORE).associateWith { Score(it) }

        val EXTRA_ACE_SCORE: Score = from(EXTRA_ACE_SCORE_RANGE)
        fun from(score: Int): Score = CACHE[score] ?: throw ScoreOutOfBoundsException(score)
    }
}
