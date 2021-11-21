package blackjack.state

import blackjack.model.Card
import blackjack.model.Cards

class Ready(override val cards: Cards = Cards.empty()) : State {

    override fun profit(amount: Double): Double = amount

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
