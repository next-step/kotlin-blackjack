package blackjack.state

import blackjack.model.Amount
import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Profit

interface State {

    val cards: Cards

    fun draw(card: Card): State

    fun profit(amount: Amount, state: State): Profit

    fun stay(): State
}
