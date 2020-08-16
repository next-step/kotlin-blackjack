package blackjack.model.status

data class Score(
    private val score: Int
) {
    fun isBurst() = score > BLACKJACK

    fun isBlackJack() = score == BLACKJACK

    override fun toString() = score.toString()

    companion object {
        const val BLACKJACK = 21
    }
}
