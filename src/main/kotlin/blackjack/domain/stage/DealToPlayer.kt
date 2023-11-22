package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayerAction
import blackjack.domain.result.DealToPlayerResult

class DealToPlayer : CardDistributor {
    override fun invoke(game: BlackJackGame): DealToPlayerResult {
        game.askHitOrStand().also {
            when (it) {
                PlayerAction.HIT -> {
                    game.dealCardToPlayerInTurn()
                    if (game.isPlayerInTurnScoreOverMax) {
                        game.passTurnToNextPlayer()
                    }
                }

                PlayerAction.STAND -> when (game.isLastTurn) {
                    true -> game.setDistributor(DealToDealer())
                    false -> game.passTurnToNextPlayer()
                }
            }
        }
        return DealToPlayerResult(game.playerInTurn)
    }
}
