package blackjack.domain.state.finished

import blackjack.domain.Card
import blackjack.domain.Cards
import java.math.BigDecimal

class Bust(
    cards: Cards
) : Finished(cards) {
    override val earningRatio: BigDecimal
        get() = BigDecimal("-1")
}
