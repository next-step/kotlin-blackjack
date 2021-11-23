package blackjack.state

import blackjack.model.Amount
import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Profit

class Ready(override val cards: Cards = Cards.empty()) : State {

    override fun profit(amount: Amount, state: State): Profit {
        throw UnsupportedOperationException("cannot support profit in running state")
    }

    override fun draw(card: Card): State {
        val cards = cards + card
        return when {
            cards.size < 2 -> Ready(cards)
            cards.isBlackjack() -> Blackjack(cards)
            else -> Hit(cards)
        }
    }

    override fun stay(): State = this
}
