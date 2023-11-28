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
            Action.HIT -> table.dealToPlayerInTurn(DISTRIBUTION_COUNT)
            Action.STAND -> {
                finishDistributionIfLastTurn(table.isLastPlayerTurn, decideDistributor)
                table.passPlayerTurnIfNotLastTurn()
            }
        }

        val isSystemStand = action == Action.STAND && playerInTurn.isBust
        return DealToPlayerResult(playerInTurn, isSystemStand)
    }

    private fun finishDistributionIfLastTurn(isLastPlayerStand: Boolean, decideDistributor: (distributor: CardDistributor) -> Unit) {
        if (isLastPlayerStand) decideDistributor(DealToDealer())
    }

    companion object {
        private const val DISTRIBUTION_COUNT = 1
    }
}
