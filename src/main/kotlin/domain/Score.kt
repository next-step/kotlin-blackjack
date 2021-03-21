package domain

class Score private constructor(private val value: Int) : Comparable<Score> {

    operator fun plus(that: Score) = Score(value = this.value + that.value)

    override fun compareTo(other: Score) = value.compareTo(other.value)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Score) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "Score(value=$value)"
    }

    companion object {
        val ZERO = Score(0)

        fun of(value: Int): Score {
            require(value > 0) { "점수는 0보다 커야합니다." }
            return Score(value)
        }
    }
}
