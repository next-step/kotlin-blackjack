package blackjack.dealer

import blackjack.card.Card

class Dealer {
    private val deck = DealerDeck()

    fun drawFirst(): List<Card> {
        return listOf(draw(), draw())
    }

    fun draw(): Card {
        return this.deck.draw()
    }
}
