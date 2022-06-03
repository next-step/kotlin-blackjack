package blackjack.game

import blackjack.card.Card

class Deck (cards: List<Card>) {

    private val cards = cards.toMutableList()

    fun draw(): Card {
        return cards.removeAt(0)
    }

    fun shuffle(): Deck {
        return Deck(cards.shuffled())
    }

    fun size(): Int {
        return cards.size
    }
}
