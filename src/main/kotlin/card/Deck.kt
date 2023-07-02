package card

import java.util.LinkedList
import java.util.Queue

@JvmInline
value class Deck private constructor(
    private val values: Queue<Card> = LinkedList(),
) {
    fun draw(): Card = values.poll()

    companion object {
        private val INIT_CARD_DECK =
            Denomination.values().flatMap { denomination -> Suit.values().map { suit -> Card(suit, denomination) } }

        fun of() = Deck(INIT_CARD_DECK.shuffled().toCollection(LinkedList()))
    }
}