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

        val outcome =
            when {
                player.sumOfHand() > dealer.sumOfHand() -> Outcome.WIN
                player.sumOfHand() < dealer.sumOfHand() -> Outcome.LOSS
                else -> Outcome.DRAW
            }
        return outcome
    }
}

data class GameResult(
    val player: Player,
    val outcome: Outcome,
)

enum class Outcome {
    WIN,
    LOSS,
    DRAW,
}
