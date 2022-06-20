package blackjack.player

import blackjack.card.Card

class PlayerCards {
    private val _cards = mutableListOf<Card>()
    private val card: List<Card> get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun show(): List<Card> {
        return card
    }
}
