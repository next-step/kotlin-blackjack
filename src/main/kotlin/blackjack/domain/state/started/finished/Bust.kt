package blackjack.domain.state.started.finished

import blackjack.domain.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class Bust(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal("-1")

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        return BigDecimal(betAmount) * earningRatio
    }
}
