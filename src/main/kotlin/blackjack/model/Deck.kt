package blackjack.model

import java.util.LinkedList
import java.util.Queue

class Deck(private val kinds: Card.Kinds, private val shape: Card.Shape) {

    override fun toString(): String = "${kinds.kindsName}${shape.shapeName}"

    fun getDeckPoints(): List<Card.Point> = Card.Point.findByKinds(kinds)

    companion object {
        const val TOTAL_DECK_SIZE = 56
        private val all: Queue<Deck> = getShuffledAllDeck()

        private fun getShuffledAllDeck(): Queue<Deck> {
            val totalDeck = mutableListOf<Deck>().apply {
                for (type in Card.Kinds.values()) {
                    for (shape in Card.Shape.values()) {
                        add(Deck(type, shape))
                    }
                }
            }.shuffled()

            return LinkedList<Deck>(totalDeck)
        }

        fun pop(): Deck = all.poll()
    }
}
