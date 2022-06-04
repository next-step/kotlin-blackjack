package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) {
            "점수는 0 이상의 숫자여야 합니다."
        }
    }

    operator fun compareTo(other: Score): Int {
        return value - other.value
    }
}
