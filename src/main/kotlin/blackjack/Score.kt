package blackjack

@JvmInline
value class Score(private val value: Int) {
    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }

    fun isLessThan(other: Score): Boolean {
        return this.value <= other.value
    }

    companion object {
        val ACE_BONUS_SCORE = Score(10)
        val ACE_BONUS_SCORE_BASE_LINE = Score(11)
    }
}
