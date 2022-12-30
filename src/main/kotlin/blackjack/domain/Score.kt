package blackjack.domain

@JvmInline
value class Score(val value: Int) {
    fun isBurst(): Boolean {
        return value > BURST_SCORE
    }

    companion object {
        const val BURST_SCORE = 21
    }
}
