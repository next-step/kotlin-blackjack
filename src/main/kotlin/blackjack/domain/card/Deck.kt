package blackjack.domain.card

import java.util.*

class Deck {
    private val value: Stack<Card> = Stack()

    init {
        initializeDeck()
    }

    private fun initializeDeck() {
        value.addAll(Card.CARD_PACK_CACHE.values.shuffled())
    }

    fun draw(): Card {
        if (value.isEmpty()) {
            initializeDeck()
        }
        return value.pop()
    }
}
