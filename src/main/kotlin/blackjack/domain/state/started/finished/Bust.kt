package blackjack.domain.state.started.finished

import blackjack.domain.Cards
import java.math.BigDecimal

class Bust(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal("-1")
}
