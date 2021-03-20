package blackjack.domain.deck

import blackjack.domain.Card

class Deck(elements: Set<Card>) {
    private val elements = elements.toMutableList()

    init {
        require(elements.size == DECK_INIT_SIZE)
    }

    fun draw(): Card {
        check(elements.isNotEmpty())
        return elements.removeAt(0)
    }

    companion object {
        private const val DECK_INIT_SIZE = 52
    }
}
