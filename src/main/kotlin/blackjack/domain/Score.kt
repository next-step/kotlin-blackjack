package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) {
            "점수는 0 이상의 숫자여야 합니다."
        }
    }

    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    operator fun compareTo(other: Score): Int {
        return value - other.value
    }

    companion object {
        val ZERO = Score(0)
        val BLACKJACK = Score(21)
    }
}
