package blackjack.model.state.running

import blackjack.model.card.Card
import blackjack.model.state.State
import blackjack.model.state.finished.Stay

abstract class Running : State {

    override val isFinished: Boolean = false
    override val earningsRate: Double = -1.0

    override fun addCard(newCard: Card): State = Hit(cards + newCard)

    override fun stay(): State = Stay(cards)

    override fun profit(dealerState: State, betMoney: Int): Double = earningsRate * betMoney
}
