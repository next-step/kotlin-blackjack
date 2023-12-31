package blackjack.domain.distirbution

import blackjack.domain.GameTable
import blackjack.domain.result.Result

abstract class CardDistributor {
    protected var _nextDistributor: CardDistributor? = null
    val nextDistributor: CardDistributor
        get() {
            requireNotNull(_nextDistributor) { "아직 배분이 진행 되지 않았습니다" }
            return _nextDistributor as CardDistributor
        }

    open fun nextDistributor(): CardDistributor {
        requireNotNull(_nextDistributor) { "아직 배분이 진행 되지 않았습니다" }
        return _nextDistributor as CardDistributor
    }

    abstract val table: GameTable
    abstract fun deal(): Result
}
