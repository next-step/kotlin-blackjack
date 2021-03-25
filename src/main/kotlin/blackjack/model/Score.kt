package blackjack.model

data class Score(val value: Int) : Comparable<Score> {
    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    operator fun minus(other: Score): Score {
        return Score(value - other.value)
    }

    override operator fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }

    companion object {
        val MAXIMUM = Score(21)

        val ZERO = Score(0)
    }
}
