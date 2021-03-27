package blackjack.model.score

data class Score(val value: Int) : Comparable<Score> {
    fun isValid(): Boolean {
        return this in ZERO..MAXIMUM
    }

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
        private val MAXIMUM = Score(21)

        val ZERO = Score(0)
    }
}
