package blackjack.domain.strategy.draw

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

interface DrawStrategy {
    fun draw(cardDeck: CardDeck): List<Card>
}
