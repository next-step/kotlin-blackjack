package blackjack.domain

private const val MIN_VALUE = 0
@JvmInline
value class Score(
    private val value: Int
) {
    init {
        require(value > MIN_VALUE) { "점수 객체는 $MIN_VALUE 보다 커야합니다." }
    }
}
