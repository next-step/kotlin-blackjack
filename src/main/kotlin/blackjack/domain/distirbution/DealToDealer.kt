package blackjack.domain.distirbution

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DealToDealerResult

class DealToDealer(
    override val table: GameTable
) : CardDistributor() {
    override fun deal(): DealToDealerResult {
        val isHit = table.dealerAction == Action.HIT
        if (isHit) table.dealToDealer(COUNT_TO_DEAL)
        _nextDistributor = DealEnd(table)
        return DealToDealerResult(isHit)
    }

    companion object {
        private const val COUNT_TO_DEAL = 1
    }
}
