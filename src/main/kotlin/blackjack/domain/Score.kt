package blackjack.domain

/**
 * Created by Jaesungchi on 2022.06.14..
 */
@JvmInline
value class Score(val value: Int) {

    fun isBust(): Boolean {
        return value > BLACKJACK_SCORE
    }

    fun isBlackJackScore(): Boolean {
        return value == BLACKJACK_SCORE
    }

    operator fun compareTo(score: Score): Int {
        return compareValues(this.value, score.value)
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        const val ACE_SUB_SCORE = 10
    }
}
