package domain

import domain.card.Card

class Player(val name: String) {
    private val cards = mutableSetOf<Card>()

    fun dealCard(card: Card) {
        this.cards.add(card)
    }

    fun cards(): Set<Card> {
        return cards.toSet()
    }
}
