package blackjack.domain

class PlayerResultCalculator {
    fun calculate(
        dealerScore: Int,
        playerScore: Int,
    ): PlayerResult {
        return when {
            dealerScore > Card.MAX_SUM -> PlayerResult.WIN
            playerScore > Card.MAX_SUM -> PlayerResult.LOSE
            playerScore > dealerScore -> PlayerResult.WIN
            else -> PlayerResult.LOSE
        }
    }
}
