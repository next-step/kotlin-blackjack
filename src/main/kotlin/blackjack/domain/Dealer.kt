package blackjack.domain

import blackjack.domain.strategy.CardPickStrategy

class Dealer(
    private val cardPickStrategy: CardPickStrategy
) {
    fun pickCard(cardDeck: CardDeck): Card {
        return cardPickStrategy.pick(cardDeck)
    }
}
