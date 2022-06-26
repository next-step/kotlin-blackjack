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
        if (isBust()) {
            return false
        } else if (that.isBust()) {
            return true
        } else {
            return value >= that.value
        }
    }

    operator fun plus(that: Score): Score {
        return Score(value + that.value)
    }

    companion object {
        private const val MAX_SCORE_CRITERIA = 21
        const val DELAER_HIT_CRITERIA = 16
    }
}
