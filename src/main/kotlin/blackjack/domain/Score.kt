package blackjack.domain

private const val MIN_VALUE = -1

@JvmInline
value class Score(
    val value: Int
) {
    init {
        require(value > MIN_VALUE) { "점수는 $MIN_VALUE 보다 커야합니다." }
    }

    fun isGreaterThan(other: Score): Boolean = value >= other.value

    fun isLessThan(other: Score): Boolean = value <= other.value

    fun isLess(other: Score): Boolean = value < other.value

    operator fun plus(other: Score): Score = Score(value.plus(other.value))

    companion object {
        val BLACK_JACK = Score(21)
    }
}
