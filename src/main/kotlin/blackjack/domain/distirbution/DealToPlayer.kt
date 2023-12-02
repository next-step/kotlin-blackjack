package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DealToPlayerResult

class DealToPlayer(
    override val table: GameTable
) : CardDistributor() {

    override fun deal(): DealToPlayerResult {
        val playerInTurn = table.playerInTurn
        val action = table.playerInTurnAction

        when (action) {
            Action.HIT -> {
                table.dealToPlayerInTurn(DISTRIBUTION_COUNT)
                _nextDistributor = DealToPlayer(table)
            }

            Action.STAND -> {
                endPlayerDistributionIfLastTurn()
                table.passPlayerTurnIfNotLastTurn()
            }
        }

        val isSystemStand = (action == Action.STAND && playerInTurn.isGreaterOrEqualToMaxScore)
        return DealToPlayerResult(playerInTurn, isSystemStand)
    }

    private fun endPlayerDistributionIfLastTurn() {
        _nextDistributor = when (table.isLastPlayerTurn) {
            true -> DealToDealer(table)
            false -> DealToPlayer(table)
        }
    }

    companion object {
        private const val DISTRIBUTION_COUNT = 1
    }
}
