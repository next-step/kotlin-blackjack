package blackjack.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

object HitDrawStrategy : DrawStrategy {
    override fun draw(deck: Deck): List<Card> = listOf(deck.pop())
}
