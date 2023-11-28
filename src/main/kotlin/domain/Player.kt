package domain

import enum.GameResult

class Player(val name: String) : AbstractCardHolder() {
    var bettingAmount: Int = 0
        set(value) {
            require(value >= 0) { "베팅 금액은 음수가 될 수 없습니다." }
            field = value
        }
    var result: GameResult = GameResult.DRAW
        private set

    fun determineResult(dealerScore: Int) {
        val playerTotalScore = calculateScore()
        result = determineGameResult(playerTotalScore, dealerScore)
    }

    fun calculateFinalProfit(): Int {
        return result.calculateProfit(bettingAmount)
    }

    private fun determineGameResult(playerTotalScore: Int, dealerScore: Int): GameResult {
        val playerHasBlackjack = BlackjackRules.isBlackjack(showHand())
        return GameResult.determineForResultOfPlayer(playerTotalScore, dealerScore, playerHasBlackjack)
    }
}
