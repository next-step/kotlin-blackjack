package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class FirstTurn {
    fun draw(cards: Cards): State {
        if (cards.isBlackjack) {
            return Blackjack()
        }
        return Hit(cards)
    }
}
