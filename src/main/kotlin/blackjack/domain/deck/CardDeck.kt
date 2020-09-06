package blackjack.domain.deck

import java.util.LinkedList
import java.util.Queue

class CardDeck(
    private val cards: Queue<Card> = LinkedList()
) {

    init {
        if (cards.isEmpty()) {
            reset()
        }
    }

    fun pop(): Card {
        if (cards.isEmpty()) {
            reset()
        }
        return cards.poll()
    }

    fun size() = cards.size

    private fun reset() {
        cards.run {
            clear()
            addAll(INITIAL_DECK.shuffled())
        }
    }

    companion object {
        private val INITIAL_DECK = Pip.values().flatMap { pip -> Suit.values().map { Card(pip, it) } }
    }
}
