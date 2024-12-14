package blackjack.domain

class PlayerResultCalculator {
    fun calculate(dealerScore: Int, playerScore: Int): PlayerResult {
        return when {
            dealerScore > 21 -> PlayerResult.WIN
            playerScore > 21 -> PlayerResult.LOSE
            playerScore > dealerScore -> PlayerResult.WIN
            else -> PlayerResult.LOSE
        }
    }
}