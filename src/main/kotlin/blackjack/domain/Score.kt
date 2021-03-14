package blackjack.domain

data class Score(val value: Int) : Comparable<Score> {
    init {
        require(value >= 1)
    }

    operator fun plus(that: Score) = copy(value = this.value + that.value)

    operator fun minus(that: Score) = copy(value = this.value - that.value)

    override fun compareTo(other: Score) = value.compareTo(other = other.value)
}
