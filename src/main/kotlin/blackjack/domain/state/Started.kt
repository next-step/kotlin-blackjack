package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards

open class Started(
    private val cards: Cards = Cards()
) : State {
    override fun draw(card: Card): State {
        cards.add(card)
        return Hit(cards)
    }
}
