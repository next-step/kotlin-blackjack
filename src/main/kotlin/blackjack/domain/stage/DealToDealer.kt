package blackjack.domain.stage

import blackjack.domain.Action
import blackjack.domain.GameTable
import blackjack.domain.result.DealToDealerResult

class DealToDealer : CardDistributor {
    override fun invoke(
        table: GameTable,
        decideDistributor: (distributor: CardDistributor) -> Unit
    ): DealToDealerResult {
        val action = table.dealer.hitOrStand()
        if (action == Action.HIT) table.dealer.dealToSelf(COUNT_TO_DEAL)
        decideDistributor(DistributionEnd())
        return DealToDealerResult(action == Action.HIT)
    }

    companion object {
        private const val COUNT_TO_DEAL = 1
    }
}
