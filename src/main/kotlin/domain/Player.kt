package domain

import domain.card.Card
import domain.card.Cards

class Player(val name: String) {
    private val cards = Cards()

    fun dealCard(card: Card) {
        cards.add(card)
    }

    fun cards(): Set<Card> {
        return cards.current()
    }

    fun result(): Int {
        return cards.result()
    }

    fun canReceiveMoreCard(): Boolean {
        return cards.canReceiveMoreCard()
    }
}
