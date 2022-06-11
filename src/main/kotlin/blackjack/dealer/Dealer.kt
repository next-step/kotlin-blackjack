package blackjack.dealer

import blackjack.card.Card

class Dealer {
    private val deck = DealerDeck()

    fun draw(): Card {
        return this.deck.draw()
    }
}
