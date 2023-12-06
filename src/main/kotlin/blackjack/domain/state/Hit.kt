package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

class Hit(override val cards: Cards) : Running() {
    override fun draw(card: Card): State {
        val newCards = cards + card
        if (Score(newCards).isBlackjack()) return Blackjack(newCards)
        if (Score(newCards).isBust()) return Bust(newCards)
        return Hit(newCards)
    }
}
