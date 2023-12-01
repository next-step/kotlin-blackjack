package domain

import enum.GameResult

class Player(val name: String, bettingAmount: Int) : AbstractCardHolder() {
    val bettingAmount = Amount(bettingAmount)

    var result: GameResult = GameResult.DRAW
        private set

    fun determineResult(dealerScore: Int, dealerCards: List<Card>) {
        val playerTotalScore = calculateScore()
        result = GameResult.determineForResultOfPlayer(playerTotalScore, dealerScore, showHand(), dealerCards)
    }

    fun calculateFinalProfit(): Int {
        return result.calculateProfit(bettingAmount)
    }
}
