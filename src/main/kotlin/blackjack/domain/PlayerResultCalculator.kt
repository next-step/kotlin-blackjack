package blackjack.domain

@Deprecated("deprecated")
object PlayerResultCalculator {
    fun calculate(
        dealerScore: Int,
        playerScore: Int,
    ): GameResult {
        return when {
            dealerScore > Card.MAX_SUM -> GameResult.WIN
            playerScore > Card.MAX_SUM -> GameResult.BUST
            dealerScore > playerScore -> GameResult.LOSE
            playerScore > dealerScore -> GameResult.WIN
            else -> GameResult.PUSH
        }
    }
}
