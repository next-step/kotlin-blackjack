package blackjack.domain.strategy

import blackjack.domain.Card
import blackjack.domain.CardDeck

class SequentialCardPickStrategy : CardPickStrategy {
    override fun pick(cardDeck: CardDeck): Card {
        return cardDeck.cards.pickFirst()
    }
}
