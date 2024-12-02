package blackjack.entity

class Dealer : Participant("딜러") {
    fun shouldDrawCard(): Boolean {
        val score = calculateScore()
        return score <= 16
    }

    override fun calculateResult(score: ComparisonScore): GameResult {
        require(score is ComparisonScore.Multiple) { "플레이어 점수는 여러개입니다." }

        val dealerScore = calculateScore()
        val playerScores = score.scores

        return if (dealerScore > BLACKJACK) {
            GameResult(this, wins = 0, loses = playerScores.size)
        } else {
            calculateGameResult(dealerScore, playerScores)
        }
    }

    private fun calculateGameResult(
        dealerScore: Int,
        playerScores: List<Int>,
    ): GameResult {
        val dealerDistance = closeToBlackjack(dealerScore)
        val playerDistances = playerScores.map { closeToBlackjack(it) }

        val wins = playerDistances.count { it > dealerDistance }
        val loses = playerDistances.count { it < dealerDistance }
        val draws = playerDistances.count { it == dealerDistance }

        return GameResult(this, wins, loses, draws)
    }

    companion object {
        const val MIN_SCORE_TO_STAND = 17
        const val MAX_SCORE_TO_DRAW = 16
    }
}
