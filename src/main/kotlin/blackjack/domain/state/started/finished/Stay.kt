package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class Stay(
    cards: Cards
) : Finished(cards) {
    override fun findEarningRatio(dealerState: State): BigDecimal {

        val playerPoint = cardPointSum()
        val dealerPoint = dealerState.cardPointSum()

        if (dealerState.isBust || playerPoint > dealerPoint) {
            return EARNING_RATIO
        }

        if (dealerState.isBlackJack || playerPoint < dealerPoint) {
            return LOSING_RATIO
        }

        return NO_EARNING_RATIO
    }
}
