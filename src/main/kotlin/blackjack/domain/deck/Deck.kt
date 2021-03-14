package blackjack.domain.deck

import blackjack.domain.Card

class Deck(elements: Set<Card>) {
    private val elements = elements.toMutableList()

    init {
        require(elements.size == 52)
    }

    fun draw(): Card {
        check(elements.isNotEmpty())
        return elements.removeAt(0)
    }
}
