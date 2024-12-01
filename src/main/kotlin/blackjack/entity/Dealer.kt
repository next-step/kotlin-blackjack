package blackjack.entity

class Dealer : Participant("딜러") {
    companion object {
        const val MIN_SCORE_TO_STAND = 17
        const val MAX_SCORE_TO_DRAW = 16
    }

    fun shouldDrawCard(): Boolean {
        val score = calculateScore()
        return score <= 16
    }

    override fun calculateResult(score: ComparisonScore): GameResult {
        require(score is ComparisonScore.Multiple) { "플레이어 점수는 여러개입니다." }

        val dealerScore = calculateScore()
        val playerScores = score.scores

        return if (dealerScore > BLACKJACK) {
            GameResult(this, 0, playerScores.size)
        } else {
            val wins = playerScores.count { closeToBlackjack(it) > closeToBlackjack(dealerScore) }
            val loses = playerScores.count { closeToBlackjack(it) < closeToBlackjack(dealerScore) }
            val draws = playerScores.count { closeToBlackjack(it) == closeToBlackjack(dealerScore) }
            GameResult(this, wins, loses, draws)
        }
    }
}
