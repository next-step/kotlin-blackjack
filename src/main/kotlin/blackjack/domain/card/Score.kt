package blackjack.domain.card

import blackjack.error.ScoreOutOfBoundsException

@JvmInline
value class Score private constructor(val score: Int) {
    operator fun plus(other: Score): Score = from(this.score.plus(other.score))

    fun canAddExtraAceScore(): Boolean = (Math.addExact(score, EXTRA_ACE_SCORE) <= MAXIMUM_SCORE)

    fun isOverBlackJack(): Boolean = (score > MAXIMUM_SCORE)

    companion object {
        private const val MINIMUM_SCORE = 0
        private const val EXTRA_ACE_SCORE = 10
        private const val MAXIMUM_SCORE = 21
        private const val CACHE_LIMIT_SCORE = 32
        private val CACHE: Map<Int, Score> = (MINIMUM_SCORE..CACHE_LIMIT_SCORE).associateWith { Score(it) }

        fun extraAceScore(): Score = from(EXTRA_ACE_SCORE)
        fun from(score: Int): Score = CACHE[score] ?: throw ScoreOutOfBoundsException(score)
    }
}
