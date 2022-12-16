package blackjack.domain.strategy

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

interface CardPickStrategy {
    fun pick(cardDeck: CardDeck): Card
}
