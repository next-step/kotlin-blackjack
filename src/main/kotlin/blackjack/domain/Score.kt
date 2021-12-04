package blackjack.domain

@JvmInline
value class Score private constructor(val value: Int) : Comparable<Score> {

    operator fun plus(score: Score) = Score(this.value + score.value)

    override fun toString() = "$value"

    override fun compareTo(other: Score) = value.compareTo(other.value)

    companion object {
        private val numbers = mutableMapOf<Int, Score>()
        val TWENTY_ONE = of(21)

        fun of(value: Int): Score {
            return numbers[value] ?: Score(value).also { numbers[value] = it }
        }
    }
}
