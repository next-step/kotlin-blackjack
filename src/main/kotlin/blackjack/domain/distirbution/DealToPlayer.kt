package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DealToPlayerResult

class DealToPlayer : CardDistributor {
    override fun invoke(
        table: GameTable,
        decideDistributor: (distributor: CardDistributor) -> Unit
    ): DealToPlayerResult {
        val playerInTurn = table.playerInTurn
        val action = table.playerInTurnAction
        when (action) {
            Action.HIT -> {
                table.dealToPlayerInTurn(DISTRIBUTION_COUNT)
            }
            Action.STAND -> {
                when (table.isLastPlayerTurn) {
                    true -> decideDistributor(DealToDealer())
                    false -> table.passPlayerTurn()
                }
            }
        }
        val isSystemStand = action == Action.STAND && playerInTurn.isBust
        return DealToPlayerResult(playerInTurn, isSystemStand)
    }

    companion object {
        private const val DISTRIBUTION_COUNT = 1
    }
}
