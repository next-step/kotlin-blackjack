package blackjack.domain.deck

import java.util.LinkedList
import java.util.Queue

object Deck {
    val cards: Queue<Card> = LinkedList()
    private val INITIAL_DECK get() =
        Suits.values().flatMap { suits ->
            Denomination.values().map { denomination ->
                Card(suits, denomination)
            }
        }

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
}
