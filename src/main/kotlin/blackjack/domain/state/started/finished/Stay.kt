package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal
import java.math.RoundingMode

class Stay(
    cards: Cards
) : Finished(cards) {
    override fun findEarningRatio(dealerState: State): BigDecimal {
        if (dealerState is BlackJack) {
            return BLACKJACK_EARNING_RATIO * LOSING_RATIO
        }

        if (cardPointSum() < dealerState.cardPointSum()) {
            return LOSING_RATIO
        }

        if (cardPointSum() == dealerState.cardPointSum()) {
            return NO_EARNING_RATIO
        }

        return EARNING_RATIO
    }

}
