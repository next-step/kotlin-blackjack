package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class FirstTurn {
    fun draw(card1: Card, card2: Card): State {
        val cards = Cards(listOf(card1, card2))
        if (cards.isBlackjack) {
            return Blackjack()
        }
        return Hit(cards)
    }
}
