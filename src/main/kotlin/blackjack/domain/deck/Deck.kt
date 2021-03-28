package blackjack.domain.deck

import blackjack.domain.card.Card

class Deck(elements: List<Card>) {
    private val elements = elements.toMutableList()

    fun draw(): Card {
        require(elements.isNotEmpty())
        return elements.removeAt(0)
    }
}
