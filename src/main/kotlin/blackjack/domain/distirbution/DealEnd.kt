package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DealEndResult

class DealEnd(
    override val table: GameTable
) : CardDistributor() {

    override fun deal(): DealEndResult =
        DealEndResult(table.players, table.dealer)

    override fun nextDistributor(): CardDistributor {
        throw IllegalArgumentException("마지막 카드 배분입니다")
    }
}
