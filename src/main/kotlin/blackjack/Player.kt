package blackjack

import blackjack.card.Card

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf()
) {
    fun addCard(card: Card) {
        this.cards.add(card)
    }
}
