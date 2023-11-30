package domain

import enum.GameResult

class Player(val name: String, bettingAmount: Int) : AbstractCardHolder() {
    val bettingAmount = Amount(bettingAmount)

    var result: GameResult = GameResult.DRAW
        private set

    fun determineResult(dealerScore: Int) {
        val playerTotalScore = calculateScore()
        result = determineGameResult(playerTotalScore, dealerScore)
    }

    fun calculateFinalProfit(): Int {
        return result.calculateProfit(bettingAmount.amount)
    }

    private fun determineGameResult(playerTotalScore: Int, dealerScore: Int): GameResult {
        val playerHasBlackjack = BlackjackRules.isBlackjack(showHand())
        return GameResult.determineForResultOfPlayer(playerTotalScore, dealerScore, playerHasBlackjack)
    }
}
