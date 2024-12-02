package blackjack

class GameJudge(private val judgeOutcomeStrategy: BlackJackJudgeOutcomeStrategy = DefaultBlackJackJudgeOutcomeStrategy()) {
    fun judge(
        dealer: Dealer,
        players: List<Player>,
    ): List<GameResult> {
        val result =
            players.map { player ->
                val outcome = judgeOutcome(player, dealer)
                GameResult(player, outcome)
            }
        return result
    }

    private fun judgeOutcome(
        player: Player,
        dealer: Dealer,
    ): Outcome {
        return judgeOutcomeStrategy.judgeOutcome(dealer, player)
    }

    fun summarizeDealerResult(gameResults: List<GameResult>): DealerResult {
        val dealerWinCount = gameResults.count { it.outcome == Outcome.LOSS }
        val dealerLossCount = gameResults.count { it.outcome == Outcome.WIN }
        return DealerResult(dealerWinCount, dealerLossCount)
    }
}

data class DealerResult(val winCount: Int, val lossCount: Int)
