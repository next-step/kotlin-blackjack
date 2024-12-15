package blackjack.domain

object BlackjackRule {
    private const val THRESHOLD_VALUE = 21
    private const val BLACKJACK_SIZE = 2

    fun isBurst(score: Int): Boolean = score > THRESHOLD_VALUE

    fun isBlackjack(
        score: Int,
        size: Int,
    ): Boolean = score == THRESHOLD_VALUE && size == BLACKJACK_SIZE
}
