package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.result.DealToPlayerResult

class DealToPlayer : CardDistributor {
    override fun invoke(game: BlackJackGame): DealToPlayerResult {
        when (game.playerInTurn.hitOrStand()) {
            Action.HIT -> game.dealCardToPlayerInTurn()
            Action.STAND -> when (game.isLastTurn) {
                true -> game.setDistributor(DealToDealer())
                false -> game.passTurnToNextPlayer()
            }
        }
        return DealToPlayerResult(game.playerInTurn)
    }
}
