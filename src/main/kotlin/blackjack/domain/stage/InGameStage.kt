package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.result.InGameResult

class InGameStage: Stage {
    private var playerChoice: PlayerAction? = null

    override fun dealCards(game: BlackJackGame) {
        playerChoice = game.askHitOrStand().also {
            when (it) {
                PlayerAction.HIT -> game.dealCardToPlayerInTurn()
                PlayerAction.STAND -> {}
            }
        }
    }

    override fun emitResult(game: BlackJackGame) {
        game.emitResult(InGameResult(game.playerInTurn))
    }

    override fun nextStage(game: BlackJackGame): Stage =
        when {
            playerChoice == null -> InGameStage()
            game.isPlayerInTurnScoreOverMax -> DetermineWinnerStage()
            playerChoice == PlayerAction.HIT -> InGameStage()
            game.isLastTurn -> DetermineWinnerStage()
            else -> {
                game.passTurnToNextPlayer()
                InGameStage()
            }
        }
}
