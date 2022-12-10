package blackjack.domain.strategy

import blackjack.domain.Card
import blackjack.domain.CardDeck

interface CardPickStrategy {
    fun pick(cardDeck: CardDeck): Card
}
