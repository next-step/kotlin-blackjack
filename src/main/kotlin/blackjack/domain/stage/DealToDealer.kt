package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.DealToDealerResult

class DealToDealer : CardDistributor {
    override fun invoke(
        table: GameTable,
        decideDistributor: (distributor: CardDistributor) -> Unit
    ): DealToDealerResult {
        val isHit = table.dealerAction == Action.HIT
        if (isHit) table.dealToDealer(COUNT_TO_DEAL)
        decideDistributor(DistributionEnd())
        return DealToDealerResult(isHit)
    }

    companion object {
        private const val COUNT_TO_DEAL = 1
    }
}
