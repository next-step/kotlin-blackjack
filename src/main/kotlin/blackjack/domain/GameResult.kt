package blackjack.domain

enum class GameResult {
    PLAYER_WON,
    PLAYER_BLACKJACK_WON,
    DEALER_WON,
    DRAW;

    fun getProfit(betAmount: Double): Double =
        when (this) {
            PLAYER_BLACKJACK_WON -> betAmount * BLACKJACK_WIN_BONUS_RATE
            PLAYER_WON -> betAmount
            DEALER_WON -> -betAmount
            else -> 0.0
        }

    companion object {
        private const val BLACKJACK_WIN_BONUS_RATE = 1.5

        fun from(dealerScore: Score, playerScore: Score): GameResult =
            when {
                dealerScore.isBust -> PLAYER_WON
                playerScore.isBust -> DEALER_WON
                playerScore.isBlackjack && dealerScore.isBlackjack -> DRAW
                playerScore.isBlackjack -> PLAYER_BLACKJACK_WON
                playerScore > dealerScore -> PLAYER_WON
                dealerScore > playerScore -> DEALER_WON
                else -> DRAW
            }
    }
}
