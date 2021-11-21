package blackjack.state

import blackjack.model.Card
import blackjack.model.Cards

class Hit(cards: Cards) : Running(cards) {

    override fun draw(card: Card): State {
        val cards = cards.add(card)
        if (cards.isBust()) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun stay(): State = Stay(cards)
}
