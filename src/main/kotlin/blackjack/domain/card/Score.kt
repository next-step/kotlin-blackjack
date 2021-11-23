package blackjack.domain.card

import blackjack.domain.player.Dealer.Companion.DEALER_DRAW_STANDARD
import blackjack.error.ScoreOutOfBoundsException

@JvmInline
value class Score private constructor(val score: Int) {

    operator fun plus(other: Score): Score = from(score + other.score)

    fun canPlusExtraAceScore(): Boolean = ((score + EXTRA_ACE_SCORE_RANGE) <= MAXIMUM_SCORE)

    fun isBust(): Boolean = (score > MAXIMUM_SCORE)

    fun isMaximumScore(): Boolean = (score == MAXIMUM_SCORE)

    fun isOverDealerDrawStandard(): Boolean = (score > DEALER_DRAW_STANDARD)

    fun compareTo(other: Score): Int = score.compareTo(other.score)

    companion object {
        private const val MINIMUM_SCORE = 0
        private const val MAXIMUM_SCORE = 21
        private const val EXTRA_ACE_SCORE_RANGE = 10
        private const val CACHE_LIMIT_SCORE = 32
        private val CACHE: Map<Int, Score> = (MINIMUM_SCORE..CACHE_LIMIT_SCORE).associateWith { Score(it) }

        val ZERO: Score = from(MINIMUM_SCORE)
        val EXTRA_ACE_SCORE: Score = from(EXTRA_ACE_SCORE_RANGE)

        fun from(score: Int): Score = CACHE[score] ?: throw ScoreOutOfBoundsException(score)
    }
}
