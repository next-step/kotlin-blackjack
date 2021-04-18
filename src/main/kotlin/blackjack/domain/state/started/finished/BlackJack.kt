package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal
import java.math.RoundingMode

class BlackJack(
    cards: Cards
) : Finished(cards) {
    public override val earningRatio: BigDecimal
        get() = BigDecimal("1.5")

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        if (dealerState is BlackJack) {
            return BigDecimal.ZERO
        }
        return BigDecimal(betAmount).multiply(earningRatio).setScale(0, RoundingMode.FLOOR)
    }
}
