package blackjack.domain

import kotlin.math.abs

val BUST_SCORE = Score(21)

@JvmInline
value class Score(val value: Int) : Comparable<Score> {
    fun isBust(): Boolean {
        return value > BUST_SCORE.value
    }

    fun abs(): Int {
        return abs(value)
    }

    operator fun minus(other: Score): Score {
        return Score(value - other.value)
    }

    operator fun plus(other: Score): Score {
        return Score(value + other.value)
    }

    override fun compareTo(other: Score): Int {
        return value - other.value
    }
}
