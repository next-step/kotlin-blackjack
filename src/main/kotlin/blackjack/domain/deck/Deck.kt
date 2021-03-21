package blackjack.domain.deck

import blackjack.domain.Card

class Deck(elements: Set<Card>) {
    private val elements = elements.toMutableSet()

    init {
        require(elements.size == DECK_INIT_SIZE)
    }

    fun draw(): Card {
        check(elements.isNotEmpty())
        return elements.removeFirst()
    }

    companion object {
        private const val DECK_INIT_SIZE = 52
    }
}

private fun <E> MutableSet<E>.removeFirst(): E {
    return this.first().also { this.remove(it) }
}
