package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) { "점수는 0보다 커야 합니다" }
    }
}
