package blackjack.domain

import blackjack.error.ScoreOutOfBoundsException

@JvmInline
value class Score private constructor(private val score: Int) {
    operator fun plus(other: Score): Score = from(this.score + other.score)

    companion object {
        private const val MINIMUM_SCORE = 0
        private const val MAXIMUM_SCORE = 32
        private val CACHE: Map<Int, Score> = (MINIMUM_SCORE..MAXIMUM_SCORE).associateWith { Score(it) }

        fun from(score: Int): Score = CACHE[score] ?: throw ScoreOutOfBoundsException(score)
    }
}
