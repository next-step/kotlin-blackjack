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
}
