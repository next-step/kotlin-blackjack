package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

class Hit(val cards: Cards) : Running() {
    override fun draw(card: Card): State {
        cards.add(card)
        if (cards.isBlackjack()) { return Blackjack() }
        if (cards.isBust()) { return Bust() }
        return Hit(cards)
    }
    fun stand(): Stand {
        return Stand()
    }
}
