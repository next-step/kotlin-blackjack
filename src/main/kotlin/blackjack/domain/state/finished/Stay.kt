package blackjack.domain.state.finished

import blackjack.domain.Cards
import java.math.BigDecimal

class Stay(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal.ONE
}
