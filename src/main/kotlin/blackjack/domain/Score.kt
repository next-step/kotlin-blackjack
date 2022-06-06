package blackjack.domain

private const val MIN_VALUE = -1
private const val ZERO = 0
private const val ELEVEN = 11
private const val BLACKJACK = 21

@JvmInline
value class Score(
    val value: Int
) : Comparable<Score> {
    init {
        require(value > MIN_VALUE) { "점수는 $MIN_VALUE 보다 커야합니다." }
    }

    operator fun plus(other: Score): Score = Score(value.plus(other.value))

    override operator fun compareTo(other: Score): Int = value.compareTo(other.value)

    companion object {
        private val SCORE_CACHE = (ZERO..ELEVEN).associateWith { Score(it) }

        val BLACKJACK = Score(blackjack.domain.BLACKJACK)

        fun of(value: Int): Score = SCORE_CACHE[value] ?: Score(value)

        fun zero(): Score = SCORE_CACHE[ZERO] ?: Score(ZERO)
    }
}
