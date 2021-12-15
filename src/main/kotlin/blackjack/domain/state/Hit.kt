package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(
    override val cards: Cards
) : Running(cards) {

    override fun draw(card: Card): State {
        val cards = cards + card
        if (cards.isBust) {
            return Bust(cards)
        }
        return Hit(cards)
    }
}
