package blackjack.domain

@JvmInline
value class Score(val score: Int) {

    fun isBurst(): Boolean {
        return score > BLACK_JACK_SCORE
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}
