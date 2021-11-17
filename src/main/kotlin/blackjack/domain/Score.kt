package blackjack.domain

import blackjack.error.ScoreOutOfBoundsException

@JvmInline
value class Score private constructor(val score: Int) {
    operator fun plus(other: Score): Score = from(this.score.plus(other.score))

    fun canAdditionalAceScore(): Boolean =
        (Math.addExact(score, ADDITIONAL_ACE_SCORE) <= BLACK_JACK)

    fun isOverBlackJack(): Boolean = (score > BLACK_JACK)

    companion object {
        private const val MINIMUM_SCORE = 0
        private const val MAXIMUM_SCORE = 32
        private const val BLACK_JACK = 21
        private const val ADDITIONAL_ACE_SCORE = 10
        private val CACHE: Map<Int, Score> = (MINIMUM_SCORE..MAXIMUM_SCORE).associateWith { Score(it) }

        fun additionalAceScore(): Score = from(ADDITIONAL_ACE_SCORE)
        fun from(score: Int): Score = CACHE[score] ?: throw ScoreOutOfBoundsException(score)
    }
}
