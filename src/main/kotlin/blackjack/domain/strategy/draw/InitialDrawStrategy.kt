package blackjack.domain.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

object InitialDrawStrategy : DrawStrategy {

    private const val INITIAL_DRAW_COUNT = 2

    override fun draw(cardDeck: CardDeck): List<Card> = List(INITIAL_DRAW_COUNT) { cardDeck.next() }
}
