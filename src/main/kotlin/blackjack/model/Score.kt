package blackjack.model

@JvmInline
value class Score(val value: Int) {

    init {
        require(value > 0) {
            "1보다 작은 수는 들어올 수 없습니다."
        }
    }

    fun isBust(): Boolean {
        return value > MAX_SCORE_CRITERIA
    }

    fun isWinThan(that: Score): Boolean {
        return when {
            isBust() -> false
            that.isBust() -> true
            else -> value >= that.value
        }
    }

    operator fun plus(that: Score): Score {
        return Score(value + that.value)
    }

    companion object {
        private const val MAX_SCORE_CRITERIA = 21
        const val DELAER_HIT_CRITERIA = 16
        const val DELAER_SOFT_SCORE = 17
    }
}
