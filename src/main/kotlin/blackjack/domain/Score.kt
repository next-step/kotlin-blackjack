package blackjack.domain

@JvmInline
value class Score(val score: Int) {

    fun burst(): Boolean {
        return score > BLACK_JACK_SCORE
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val ACE_BONUS = 10
    }
}
