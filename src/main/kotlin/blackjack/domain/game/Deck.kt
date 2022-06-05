package blackjack.domain.game

import blackjack.domain.card.Card

class Deck(cards: List<Card>) {
    private val cards = cards.toMutableList()

    fun draw(): Card {
        return cards.removeAt(0)
    }

    fun shuffle(): Deck {
        val shuffledCards = cards.shuffled()
        return if (cards != shuffledCards) {
            Deck(shuffledCards)
        } else {
            shuffle()
        }
    }

    fun size(): Int {
        return cards.size
    }
}
