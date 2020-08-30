package blackjack.model.state.finished

import blackjack.model.card.Cards
import blackjack.model.state.State

class Bust(override val cards: Cards) : Finished() {
    override val earningsRate: Double = -1.0

    override fun profit(dealerState: State, betMoney: Int): Double = earningsRate * betMoney
}
