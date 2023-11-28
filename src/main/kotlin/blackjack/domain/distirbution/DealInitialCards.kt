package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DealInitialCardResult

class DealInitialCards(
    override val table: GameTable
) : CardDistributor() {

    override fun deal(): DealInitialCardResult {
        table.dealToAll(INITIAL_DISTRIBUTION_COUNT)
        _nextDistributor = DealToPlayer(table)
        return DealInitialCardResult(table.dealer, table.players)
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
