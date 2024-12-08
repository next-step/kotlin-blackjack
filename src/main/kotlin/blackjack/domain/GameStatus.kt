package blackjack.domain

enum class GameStatus {
    PLAYING,
    STAY,
    BURST,
    ;

    fun isPlayable(): Boolean = this == PLAYING

    companion object {
        private const val BLACKJACK_VALUE = 21
        private const val DEALER_DRAW_THRESHOLD = 16

        fun isBurst(score: Int): Boolean = score > BLACKJACK_VALUE

        fun isBlackjack(score: Int): Boolean = score == BLACKJACK_VALUE

        fun isOverDealerThreshold(score: Int): Boolean = score > DEALER_DRAW_THRESHOLD
    }
}
