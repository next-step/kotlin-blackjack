package blackjack.model.state.finished

import blackjack.model.card.Cards
import blackjack.model.state.State

class BlackJack(override val cards: Cards) : Finished() {

    override val earningsRate: Double = 1.5

    override fun profit(dealerState: State, betMoney: Int): Double {
        return when {
            dealerState.cards.isBlackJack() -> 0.0
            else -> earningsRate * betMoney
        }
    }
}
