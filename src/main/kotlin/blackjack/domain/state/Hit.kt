package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

class Hit(val cards: Cards) : Running() {
    override fun draw(card: Card): State {
        cards.add(card)
        if (Score(cards).isBlackjack()) return Blackjack()
        if (Score(cards).isBust()) return Bust()
        return Hit(cards)
    }

    fun stand(): Stand {
        return Stand()
    }
}
