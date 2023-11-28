package enum

import domain.BlackjackRules

enum class GameResult {
    WIN,
    LOSE,
    DRAW,
    BLACKJACK_WIN;

    fun calculateProfit(bettingAmount: Int): Int {
        return when (this) {
            WIN -> bettingAmount * 2
            BLACKJACK_WIN -> (bettingAmount * 1.5).toInt()
            LOSE -> -bettingAmount
            DRAW -> 0
        }
    }

    companion object {
        fun determineForResultOfPlayer(playerTotalScore: Int, dealerTotalScore: Int, playerHasBlackjack: Boolean): GameResult {
            if (playerHasBlackjack) {
                return if (dealerTotalScore == BlackjackRules.MAXIMUM_SCORE) DRAW else BLACKJACK_WIN
            }
            return when {
                playerTotalScore > BlackjackRules.MAXIMUM_SCORE -> LOSE
                dealerTotalScore > BlackjackRules.MAXIMUM_SCORE -> WIN
                playerTotalScore > dealerTotalScore -> WIN
                playerTotalScore == dealerTotalScore -> DRAW
                else -> LOSE
            }
        }
    }
}
