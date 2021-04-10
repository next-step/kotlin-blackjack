package blackjack.domain.state.started

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.finished.Bust

class Hit(
    cards: Cards
) : Started(cards) {
    fun draw(card: Card): State {
        val newCards = cards.with(card)

        if (newCards.isBust) {
            return Bust(newCards)
        }
        return Hit(newCards)
    }
}
