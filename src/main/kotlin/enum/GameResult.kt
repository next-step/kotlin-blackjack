package enum

import domain.Amount
import domain.BlackjackRules

enum class GameResult(private val multiplier: Double) {
    WIN(2.0),
    BLACKJACK_WIN(1.5),
    LOSE(-1.0),
    DRAW(0.0);

    fun calculateProfit(bettingAmount: Amount): Int {
        return (bettingAmount.amount * multiplier).toInt()
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
