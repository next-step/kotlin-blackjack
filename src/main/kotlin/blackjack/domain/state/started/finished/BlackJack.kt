package blackjack.domain.state.started.finished

import blackjack.domain.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class BlackJack(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal("1.5")

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        if (dealerState is BlackJack) {
            return BigDecimal.ZERO
        }
        return BigDecimal(betAmount) * earningRatio
    }
}
