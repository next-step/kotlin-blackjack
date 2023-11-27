package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

open class Started(private val cards: Cards) : State {
    override fun draw(card: Card): State {
        cards.add(card)
        if (cards.isBlackjack()) { return Blackjack(cards) }
        if (cards.isBust()) { return Bust(cards) }
        return Hit(cards)
    }
}
