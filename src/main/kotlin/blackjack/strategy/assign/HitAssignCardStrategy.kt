package blackjack.strategy.assign

import blackjack.domain.card.Deck
import blackjack.domain.card.Card

object HitAssignCardStrategy : AssignCardStrategy {
    override fun assign(deck: Deck): List<Card> = deck.pop()
}
