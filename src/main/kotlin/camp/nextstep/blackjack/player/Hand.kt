package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card

class Hand {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()

    fun isEmpty() = _cards.isEmpty()

    fun add(card: Card) {
        this._cards.add(card)
    }
}
