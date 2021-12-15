package blackjack.domain.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

object HitDrawStrategy : DrawStrategy {

    override fun draw(cardDeck: CardDeck): List<Card> = listOf(cardDeck.next())
}
