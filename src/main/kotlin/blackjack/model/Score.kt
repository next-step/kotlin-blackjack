package blackjack.model

data class Score(val score: Int) : Comparable<Score> {
    operator fun plus(other: Score): Score {
        return Score(score + other.score)
    }

    operator fun minus(other: Score): Score {
        return Score(score - other.score)
    }

    override operator fun compareTo(other: Score): Int {
        return score.compareTo(other.score)
    }

    companion object {
        val MAXIMUM = Score(21)

        val ZERO = Score(0)
    }
}
