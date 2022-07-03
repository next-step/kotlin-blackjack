package blackjack.domain

@JvmInline
value class Score(val score: Int) {

    companion object {
        const val BUST_SCORE = 21
        const val DEALER_DRAW_SCORE = 17
    }
}
