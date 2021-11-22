package blackjack.state

import blackjack.model.Amount
import blackjack.model.Cards
import blackjack.model.Profit

abstract class Running(cards: Cards) : Started(cards) {

    override fun profit(amount: Amount, state: State): Profit {
        throw UnsupportedOperationException("cannot support profit in running state")
    }
}
