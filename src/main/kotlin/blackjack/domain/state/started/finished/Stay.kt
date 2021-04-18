package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal
import java.math.RoundingMode

class Stay(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal.ONE

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        if (dealerState is BlackJack) {
            return BigDecimal(betAmount).multiply(dealerState.earningRatio)
                .multiply(BigDecimal(-1))
                .setScale(0, RoundingMode.FLOOR)
        }

        if (cardPointSum() < dealerState.cardPointSum()) {
            return BigDecimal(betAmount) * BigDecimal(-1)
        }

        if (cardPointSum() == dealerState.cardPointSum()) {
            return BigDecimal.ZERO
        }

        return BigDecimal(betAmount) * earningRatio
    }
}
