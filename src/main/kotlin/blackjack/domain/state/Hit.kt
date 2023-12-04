package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

class Hit(override val cards: Cards) : Running() {
    override fun draw(card: Card): State {
        cards.add(card)
        if (Score(cards).isBlackjack()) return Blackjack(cards)
        if (Score(cards).isBust()) return Bust(cards)
        return Hit(cards)
    }

    fun stand(): Stand {
        return Stand(cards)
    }
}
