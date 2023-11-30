package enum

import domain.Amount
import domain.BlackjackRules
import domain.Card

enum class GameResult(private val multiplier: Double) {
    WIN(2.0),
    BLACKJACK_WIN(1.5),
    LOSE(-1.0),
    DRAW(0.0);

    fun calculateProfit(bettingAmount: Amount): Int {
        return (bettingAmount.amount() * multiplier).toInt()
    }

    companion object {
        fun determineForResultOfPlayer(playerTotalScore: Int, dealerTotalScore: Int, playerCards: List<Card>, dealerCards: List<Card>): GameResult {
            val playerHasBlackjack = BlackjackRules.isBlackjack(playerCards)
            val dealerHasBlackjack = BlackjackRules.isBlackjack(dealerCards)

            if (playerHasBlackjack) {
                return if (dealerHasBlackjack) DRAW else BLACKJACK_WIN
            }
            return when {
                BlackjackRules.isBust(playerTotalScore) -> LOSE
                BlackjackRules.isBust(dealerTotalScore) -> WIN
                playerTotalScore > dealerTotalScore -> WIN
                playerTotalScore == dealerTotalScore -> DRAW
                else -> LOSE
            }
        }
    }
}
