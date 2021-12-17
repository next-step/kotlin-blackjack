package blackjack.domain

import blackjack.domain.strategy.hittable.HittableStrategy

data class Score(val score: Int) {

    val isBlackjack: Boolean = score == BLACK_JACK_SCORE
    val isBust: Boolean = score > BLACK_JACK_SCORE

    init {
        require(score >= 0) { SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE.format(score) }
    }

    fun getAceScore(): Score =
        if (MAX_ACE_SCORE + score > BLACK_JACK_SCORE) {
            Score(MIN_ACE_SCORE)
        } else {
            Score(MAX_ACE_SCORE)
        }

    fun canHit(hittableStrategy: HittableStrategy): Boolean = hittableStrategy.canHit(score)

    operator fun plus(other: Score) = Score(score + other.score)

    operator fun compareTo(other: Score) = score.compareTo(other.score)

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val MIN_ACE_SCORE = 1
        private const val MAX_ACE_SCORE = 11
        const val SCORE_MUST_BE_ZERO_OR_MORE_EXCEPTION_MESSAGE = "점수는 0이상이어야 합니다. 현재 입력한 점수 == %s"
    }
}
