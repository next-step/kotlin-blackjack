package enum

import domain.BlackjackRules

enum class GameResult {
    WIN,
    LOSE,
    DRAW;

    fun calculateProfit(bettingAmount: Int): Int {
        return when (this) {
            WIN -> bettingAmount * 2
            LOSE -> -bettingAmount
            DRAW -> 0
        }
    }

    companion object {
        fun determineForResultOfPlayer(playerTotalScore: Int, dealerTotalScore: Int): GameResult {
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
