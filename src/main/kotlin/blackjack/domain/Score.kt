package blackjack.domain

import blackjack.domain.player.MatchingResult

data class Score(val value: Int) : Comparable<Score> {
    init {
        require(value >= 1)
    }

    operator fun plus(that: Score) = copy(value = this.value + that.value)

    operator fun minus(that: Score) = copy(value = this.value - that.value)

    override fun compareTo(other: Score) = value.compareTo(other = other.value)

    fun match(that: Score): MatchingResult {
        if (this > that) {
            return MatchingResult.WIN
        }

        if (this < that) {
            return MatchingResult.LOSE
        }

        return MatchingResult.DRAW
    }

    companion object {
        val DEALER_TAKEABLE_LIMIT = Score(16)
        val BLACKJACK = Score(21)
    }
}
