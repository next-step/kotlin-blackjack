package blackjack.domain.deck

import java.util.LinkedList
import java.util.Queue

class Deck {
    val cards: Queue<Card> = LinkedList()

    init {
        cards.run {
            addAll(INITIAL_DECK)
        }
    }

    fun pop(): Card {
        if (cards.isEmpty()) {
            reset()
        }
        return cards.poll()
    }

    fun reset() {
        cards.run {
            clear()
            addAll(INITIAL_DECK.shuffled())
        }
    }

    companion object {
        private val INITIAL_DECK =
            Suits.values().flatMap { suits ->
                Denomination.values().map { denomination ->
                    Card(suits, denomination)
                }
            }
    }
}
