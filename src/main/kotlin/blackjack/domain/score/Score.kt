package blackjack.domain.score

@JvmInline
value class Score(val value: Int) : Comparable<Score> {
    init {
        require(value >= 0) { "점수는 음수일 수 없습니다. score: $value" }
    }

    fun getState(): ScoreState = when (this) {
        BLACK_JACK -> ScoreState.BLACK_JACK
        in (ONE..BLACK_JACK) -> ScoreState.NORMAL
        else -> ScoreState.BUST
    }

    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }

    operator fun minus(other: Score): Score {
        return Score(this.value - other.value)
    }

    override operator fun compareTo(other: Score): Int {
        return this.value.compareTo(other.value)
    }

    companion object {
        private val BLACK_JACK = Score(21)
        val ZERO = Score(0)
        val ONE = Score(1)
        val FOR_SECOND_ACE_VALUE = Score(10)
    }
}
