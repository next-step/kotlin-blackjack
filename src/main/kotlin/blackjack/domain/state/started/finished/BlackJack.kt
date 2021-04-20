package blackjack.domain.state.started.finished

import blackjack.domain.card.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

class BlackJack(
    cards: Cards
) : Finished(cards) {
    override val isBlackJack: Boolean = true

    override fun findEarningRatio(dealerState: State): BigDecimal {
        if (dealerState.isBlackJack) {
            return NO_EARNING_RATIO
        }
        return BLACKJACK_EARNING_RATIO
    }
}
