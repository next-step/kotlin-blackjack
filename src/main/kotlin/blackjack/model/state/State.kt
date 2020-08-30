package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.Cards

interface State {
    val cards: Cards
    val isFinished: Boolean
    val earningsRate: Double

    fun addCard(newCard: Card): State

    fun stay(): State

    fun profit(dealerState: State, betMoney: Int): Double
}
