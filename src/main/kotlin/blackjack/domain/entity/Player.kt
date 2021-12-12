package blackjack.domain.entity

import blackjack.domain.deck.CardDeck

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
