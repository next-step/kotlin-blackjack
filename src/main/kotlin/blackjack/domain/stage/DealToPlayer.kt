package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.BlackJackGame
import blackjack.domain.GameTable
import blackjack.domain.result.DealToPlayerResult

class DealToPlayer : CardDistributor {
    override fun invoke(game: BlackJackGame, table: GameTable): DealToPlayerResult {
        when (game.table.playerInTurn.hitOrStand()) {
            Action.HIT -> table.dealToPlayerInTurn(DISTRIBUTION_COUNT)
            Action.STAND -> when (game.table.isLastPlayerTurn) {
                true -> game.setDistributor(DealToDealer())
                false -> table.passTurn()
            }
        }
        return DealToPlayerResult(game.table.playerInTurn)
    }

    companion object {
        private const val DISTRIBUTION_COUNT = 1
    }
}
