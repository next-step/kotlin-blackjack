package blackjack.domain.score

@JvmInline
value class Score(val value: Int) {
    init {
        require(value >= 0) { "점수는 음수일 수 없습니다. score: $value" }
    }

    fun isBust(): Boolean = this > BLACK_JACK
    fun isBlackJack(): Boolean = this == BLACK_JACK

    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }

    operator fun minus(other: Score): Score {
        return Score(this.value - other.value)
    }

    operator fun compareTo(other: Score): Int {
        return this.value.compareTo(other.value)
    }

    companion object {
        val BLACK_JACK = Score(21)
        val ZERO = Score(0)
        val FOR_SECOND_ACE_VALUE = Score(10)
    }
}
