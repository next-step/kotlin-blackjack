package blackjack

class GameJudge(private val judgeOutcomeStrategy: BlackJackJudgeOutcomeStrategy = DefaultBlackJackJudgeOutcomeStrategy()) {
    fun judge(
        dealer: Dealer,
        players: List<Player>,
    ): List<GameResult2> {
        val result =
            players.map { player ->
                val outcome = judgeOutcome(player, dealer)
                GameResult2(player, outcome)
            }
        return result
    }

    private fun judgeOutcome(
        player: Player,
        dealer: Dealer,
    ): Outcome {
        return judgeOutcomeStrategy.judgeOutcome(dealer, player)
    }
}
