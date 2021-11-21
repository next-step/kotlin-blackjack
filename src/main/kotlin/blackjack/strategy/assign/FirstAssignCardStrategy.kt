package blackjack.strategy.assign

import blackjack.domain.card.Deck
import blackjack.domain.card.Card

object FirstAssignCardStrategy : AssignCardStrategy {
    private const val TWO = 2
    override fun assign(deck: Deck): List<Card> = deck.pop(TWO)
}
