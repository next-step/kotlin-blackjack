package blackjack

@JvmInline
value class Score(private val value: Int) {
    operator fun plus(other: Score): Score {
        return Score(this.value + other.value)
    }

    fun isLessThan(other: Score): Boolean {
        return this.value <= other.value
    }

    fun isBust(): Boolean {
        return this.value >= BUST_SCORE.value
    }

    fun isBlackjack(): Boolean {
        return this == BLACKJACK_SCORE
    }

    companion object {
        val ACE_BONUS_SCORE = Score(10)
        val ACE_BONUS_SCORE_BASE_LINE = Score(11)
        val BLACKJACK_SCORE = Score(21)
        val BUST_SCORE = Score(22)
    }
}
