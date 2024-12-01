package blackjack.entity

class Player(name: String) : Participant(name) {
    override fun calculateResult(input: ComparisonScore): GameResult {
        require(input is ComparisonScore.Single) { "딜러 점수는 하나입니다." }

        val dealerScore = input.score

        val playerScore = calculateScore()
        return when {
            dealerScore > BLACKJACK -> GameResult(this, 1)
            closeToBlackjack(playerScore) < closeToBlackjack(dealerScore) -> GameResult(this, wins = 1)
            closeToBlackjack(playerScore) > closeToBlackjack(dealerScore) -> GameResult(this, loses = 1)
            else -> GameResult(this, draws = 1)
        }
    }
}