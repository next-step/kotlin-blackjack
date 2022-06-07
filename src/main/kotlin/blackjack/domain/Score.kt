package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) { "점수는 0보다 커야 합니다" }
    }

    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    operator fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }
}
