package domain

import domain.card.Card

class Player(val name: String) {
    private val cards = mutableSetOf<Card>()

    fun dealCards(cards: Set<Card>) {
        this.cards.addAll(cards)
    }

    fun cards(): Set<Card> {
        return cards.toSet()
    }
}
