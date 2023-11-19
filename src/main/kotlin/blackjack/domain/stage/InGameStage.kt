package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.result.InGameResult

class InGameStage(
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
            playerChoice == null -> InGameStage(game)
            game.isPlayerInTurnScoreOverMax -> DetermineWinnerStage(game)
            playerChoice == PlayerAction.HIT -> InGameStage(game)
            game.isLastTurn -> DetermineWinnerStage(game)
            else -> {
                game.passTurnToNextPlayer()
                InGameStage(game)
            }
        }
}
