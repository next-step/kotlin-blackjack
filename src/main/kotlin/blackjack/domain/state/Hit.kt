package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(
    override val cards: Cards
) : Running(cards) {

    override fun draw(card: Card): State {
        val drawnCards = cards + card
        if (drawnCards.isBust) {
            return Bust(drawnCards)
        }
        return Hit(drawnCards)
    }
}
