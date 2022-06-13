package blackjack.player

import blackjack.card.Card

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    private val card: List<Card> get() = _cards.toList()

    fun getCard(card: Card) {
        _cards.add(card)
    }

    fun show(): List<Card> {
        return card
    }
}
