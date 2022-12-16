package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.strategy.CardPickStrategy

class Dealer(
    name: String,
    cards: Cards = Cards(),
    private val cardPickStrategy: CardPickStrategy,
) : Player(name, cards) {

    fun pickCard(cardDeck: CardDeck): Card {
        return cardPickStrategy.pick(cardDeck)
    }

    fun pickIfRequired(cardDeck: CardDeck) {
        if (this.getScore() > DEALER_THRESHOLD)
            return
        cards.add(this.pickCard(cardDeck))
    }

    companion object {
        private const val DEALER_THRESHOLD = 16
    }
}
