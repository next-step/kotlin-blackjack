package blackjack.domain

class Dealer(
    override val name: String = "딜러",
    override val gameState: GameState = GameState(),
) : Participant(name, gameState) {
    infix fun vs(player: Player): Result {
        val result =
            when {
                isBurst -> Result.WIN
                player.isBlackjack && isBlackjack -> Result.DRAW
                player.isBlackjack -> Result.WIN
                player.isBurst -> Result.LOSE
                player.score > score -> Result.WIN
                player.score < score -> Result.LOSE
                else -> Result.DRAW
            }
        val playerBet = player.settleBet(result)
        settleBet(result, playerBet)

        return result
    }

    private fun settleBet(
        result: Result,
        playerBet: Bet,
    ) {
        if (result == Result.WIN) {
            gameState.applyBet(Bet.negative(playerBet))
        } else if (result == Result.LOSE) {
            gameState.applyBet(playerBet)
        }
    }

    fun shouldDraw(): Boolean {
        return score < DEALER_DRAW_SCORE_THRESHOLD
    }

    companion object {
        private const val DEALER_DRAW_SCORE_THRESHOLD = 16
    }
}
