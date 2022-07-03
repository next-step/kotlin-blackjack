package blackjack.domain

@JvmInline
value class Score(val score: Int) : Comparable<Score> {

    override fun compareTo(other: Score): Int {
        return this.score.compareTo(other.score)
    }

    companion object {
        const val BUST_SCORE = 21
        const val DEALER_DRAW_SCORE = 17
    }
}
