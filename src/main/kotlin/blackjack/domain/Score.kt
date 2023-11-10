package blackjack.domain

@JvmInline
value class Score(val score: Int) {

    fun burst(): Boolean {
        return score > BLACK_JACK_SCORE
    }

    operator fun compareTo(score: Score): Int {
        return this.score.compareTo(score.score)
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val ACE_BONUS = 10
    }
}
