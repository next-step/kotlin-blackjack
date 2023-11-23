package blackjack.domain.stage

import blackjack.domain.GameTable
import blackjack.domain.result.DealInitialCardResult

class DealInitialCards : CardDistributor {

    override fun invoke(
        table: GameTable,
        decideDistributor: (distributor: CardDistributor) -> Unit
    ): DealInitialCardResult {
        table.dealToAll(INITIAL_DISTRIBUTION_COUNT)
        decideDistributor(DealToPlayer())
        return DealInitialCardResult(table.dealer, table.players)
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
