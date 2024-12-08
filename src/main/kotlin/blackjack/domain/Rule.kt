package blackjack.domain

object Rule {
    private const val BLACKJACK_VALUE = 21
    private const val DEALER_DRAW_THRESHOLD = 16

    fun checkBurst(score: Int): Boolean = score > BLACKJACK_VALUE

    fun checkBlackjack(score: Int): Boolean = score == BLACKJACK_VALUE

    fun checkDealerScoreThreshold(score: Int): Boolean = score > DEALER_DRAW_THRESHOLD
}
