package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.CardScoreCalculator

class Hit(val cards: Cards) : Running() {
    override fun draw(card: Card): State {
        cards.add(card)
        if (CardScoreCalculator.isBlackjack(cards)) return Blackjack()
        if (CardScoreCalculator.isBust(cards)) return Bust()
        return Hit(cards)
    }

    fun stand(): Stand {
        return Stand()
    }
}
