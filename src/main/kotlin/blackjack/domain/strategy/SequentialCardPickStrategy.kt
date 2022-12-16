package blackjack.domain.strategy

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

class SequentialCardPickStrategy : CardPickStrategy {
    override fun pick(cardDeck: CardDeck): Card {
        return cardDeck.cards.pickFirst()
    }
}
