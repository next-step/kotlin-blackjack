package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.result.InGameResult

class InGame(
    private val game: BlackJackGame,
) : Stage {
    private val playerChoice by lazy {
        game.askHitOrStand()
    }

    override fun progress() {
        when (playerChoice) {
            PlayerAction.HIT -> game.dealCardToPlayerInTurn()
            else -> TODO()
        }
    }

    override fun emitResult() {
        game.emitResult(InGameResult(game.playerInTurn))
    }

    override fun nextStage(): Stage {
        if (game.isPlayerInTurnScoreOverMax) return End(game)
        return InGame(game)
    }
}
