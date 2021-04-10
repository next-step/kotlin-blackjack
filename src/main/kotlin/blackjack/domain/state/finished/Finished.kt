package blackjack.domain.state.finished

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import java.math.BigDecimal

abstract class Finished(
    cards: Cards
) : State {
    abstract val earningRatio: BigDecimal
}
