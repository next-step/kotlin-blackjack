package blackjack.domain.state.started.finished

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class Stay(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal.ONE

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        if (cardPointSum() < dealerState.cardPointSum()) {
            return BigDecimal(betAmount) * BigDecimal(-1)
        }

        if (cardPointSum() == dealerState.cardPointSum()) {
            return BigDecimal.ZERO
        }

        return BigDecimal(betAmount) * earningRatio
    }
}
