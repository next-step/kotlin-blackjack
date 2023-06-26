package blackjack.domain.model

import blackjack.domain.PointCalculator

class Cards(cards: List<Card> = emptyList(), trump: Trump) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val items: List<Card> get() = _cards.toList()
    val sum: Int get() = PointCalculator.sum(this)

    init {
        if (_cards.isEmpty()) {
            _cards.add(trump.getCard())
            _cards.add(trump.getCard())
        }
    }

    fun add(card: Card) {
        _cards.add(card)
    }
}
