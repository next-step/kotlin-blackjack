package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.result.InGameResult

class InGame(
    private val game: BlackJackGame,
) : Stage {
    private var playerChoice: PlayerAction? = null

    override fun progress() {
        playerChoice = game.askHitOrStand().also {
            when (it) {
                PlayerAction.HIT -> game.dealCardToPlayerInTurn()
                PlayerAction.STAND -> {}
            }
        }
    }

    override fun emitResult() {
        game.emitResult(InGameResult(game.playerInTurn))
    }

    override fun nextStage(): Stage =
        when {
            playerChoice == null -> InGame(game)
            game.isPlayerInTurnScoreOverMax -> End(game)
            playerChoice == PlayerAction.HIT -> InGame(game)
            game.isLastTurn -> End(game)
            else -> {
                game.passTurnToNextPlayer()
                InGame(game)
            }
        }
}
