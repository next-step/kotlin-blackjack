package blackjack.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.Deck

object ReadyDrawStrategy : DrawStrategy {
    private const val START = 1
    private const val END = 2

    override fun draw(deck: Deck): List<Card> = (START..END).map { deck.pop() }
}
