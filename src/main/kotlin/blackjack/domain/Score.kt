package blackjack.domain

@JvmInline
value class Score(
    val value: Int
) : Comparable<Score> {
    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    override fun compareTo(other: Score): Int {
        return value - other.value
    }
}
