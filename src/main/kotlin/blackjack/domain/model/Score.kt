package blackjack.domain.model

@JvmInline
value class Score(val score: Int) : Comparable<Score> {
    fun isBust(): Boolean {
        return score > WINNING_SCORE
    }

    fun isWinningScore(): Boolean {
        return score == WINNING_SCORE
    }

    fun ltWinningScore(): Boolean {
        return score < WINNING_SCORE
    }

    fun lteWinningScore(): Boolean {
        return score <= WINNING_SCORE
    }

    operator fun plus(score: Score): Score {
        return Score(this.score + score.score)
    }

    operator fun minus(score: Int): Score {
        return Score(this.score - score)
    }

    operator fun compareTo(score: Int): Int {
        return this.score - score
    }

    override fun compareTo(other: Score): Int {
        return this.score - other.score
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
