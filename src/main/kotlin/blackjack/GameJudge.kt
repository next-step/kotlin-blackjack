package blackjack

class GameJudge {
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
        if (player.isBust()) {
            return Outcome.LOSS
        }
        if (dealer.isBust()) {
            return Outcome.WIN
        }

        return when {
            player.sumOfHand() > dealer.sumOfHand() -> Outcome.WIN
            player.sumOfHand() < dealer.sumOfHand() -> Outcome.LOSS
            else -> Outcome.DRAW
        }
    }
}
