package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.DealToPlayerResult

class DealToPlayer : CardDistributor {
    override fun invoke(
        table: GameTable,
        decideDistributor: (distributor: CardDistributor) -> Unit
    ): DealToPlayerResult {
        when (table.playerInTurnAction) {
            Action.HIT -> table.dealToPlayerInTurn(DISTRIBUTION_COUNT)
            Action.STAND -> when (table.isLastPlayerTurn) {
                true -> decideDistributor(DealToDealer())
                false -> table.passPlayerTurn()
            }
        }
        return DealToPlayerResult(table.playerInTurn)
    }

    companion object {
        private const val DISTRIBUTION_COUNT = 1
    }
}
