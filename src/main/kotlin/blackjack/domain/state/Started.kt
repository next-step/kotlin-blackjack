package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.CardScoreCalculator

open class Started(
    private val cards: Cards = Cards()
) : State {
    override fun draw(card: Card): State {
        cards.add(card)
        if (cards.size < 2) return Started(cards)
        if (CardScoreCalculator.isBlackjack(cards)) return Blackjack()
        return Hit(cards)
    }
}
