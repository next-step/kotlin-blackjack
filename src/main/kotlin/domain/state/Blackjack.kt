package domain.state

import domain.card.Cards
import java.math.BigDecimal

class Blackjack(cards: Cards) : TerminationState(cards) {

    override fun getRevenueRate(dealerState: State): BigDecimal =
        if (dealerState !is Blackjack) { BLACKJACK_REVENUE_RATE } else { super.getRevenueRate(dealerState) }

    companion object {
        private val BLACKJACK_REVENUE_RATE = BigDecimal("1.5")
    }
}
