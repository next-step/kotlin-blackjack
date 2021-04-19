package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class Bust(
    cards: Cards
) : Finished(cards) {
    override val isBust: Boolean
        get() = true

    override fun findEarningRatio(dealerState: State): BigDecimal {
        return LOSING_RATIO
    }
}
