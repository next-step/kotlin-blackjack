package blackjack.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

object HitDrawStrategy : DrawStrategy {
    override fun assign(deck: Deck): List<Card> = listOf(deck.pop())
}
