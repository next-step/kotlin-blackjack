package blackjack.domain.deck

import blackjack.domain.Card

class Deck(elements: List<Card>) {
    private val _elements = elements.toMutableList()

    fun draw(): Card {
        require(_elements.isNotEmpty())
        return _elements.removeAt(0)
    }
}
