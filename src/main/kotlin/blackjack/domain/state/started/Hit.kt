package blackjack.domain.state.started

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.finished.Bust

class Hit(
    cards: Cards
) : Started(cards) {

    override fun takeCard(card: Card): State {
        val newCards = cards.with(card)

        if (newCards.isBust) {
            return Bust(newCards)
        }
        return Hit(newCards)
    }
}
