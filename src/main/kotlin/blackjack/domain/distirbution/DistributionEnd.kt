package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.distribution.DistributionEndResult

class DistributionEnd(
    override val table: GameTable
) : CardDistributor() {

    override fun deal(): DistributionEndResult {
        throw IllegalArgumentException("더 이상 카드를 배분할 수 없습니다")
    }
}
