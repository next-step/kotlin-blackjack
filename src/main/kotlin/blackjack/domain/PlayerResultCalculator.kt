package blackjack.domain

@Deprecated("deprecated - step3 에서만 사용됨")
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
