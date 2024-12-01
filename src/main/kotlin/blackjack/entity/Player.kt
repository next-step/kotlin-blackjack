package blackjack.entity

class Player(name: String) : Participant(name) {
    override fun calculateResult(score: ComparisonScore): GameResult {
        require(score is ComparisonScore.Single) { "딜러 점수는 하나입니다." }

        val dealerScore = score.score
        val playerScore = calculateScore()

        return calculateGameResult(playerScore, dealerScore)
    }

    private fun calculateGameResult(
        playerScore: Int,
        dealerScore: Int,
    ): GameResult {
        val playerDistance = closeToBlackjack(playerScore)
        val dealerDistance = closeToBlackjack(dealerScore)

        return when {
            dealerScore > BLACKJACK -> GameResult(this, 1)
            playerDistance < dealerDistance -> GameResult(this, wins = 1)
            playerDistance > dealerDistance -> GameResult(this, loses = 1)
            else -> GameResult(this, draws = 1)
        }
    }
}
