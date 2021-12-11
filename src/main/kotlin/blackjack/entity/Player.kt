package blackjack.entity

import blackjack.deck.CardDeck

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf(),
) {

    override fun toString(): String {
        return name
    }

    fun hits() {
        cards.add(CardDeck.draw())
    }
}
