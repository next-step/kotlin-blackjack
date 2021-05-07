package blackjack.model

import blackjack.model.trump.Card
import blackjack.model.trump.Deck

class MockDeck(
    private val deck: MutableList<Card>
) : Deck, MutableList<Card> by deck {
    constructor(vararg cards: Card) : this(cards.toMutableList())

    override fun draw(): Card {
        return deck.removeAt(0)
    }
}
