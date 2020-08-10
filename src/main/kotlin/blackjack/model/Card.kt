package blackjack.model

import java.util.LinkedList
import java.util.Queue

class Card(private val kinds: Kinds, private val shape: Shape) {

    override fun toString(): String = "${kinds.kindsName}${shape.shapeName}"

    fun getCardPoints(): List<Point> = Point.findByKinds(kinds)

    companion object {
        const val TOTAL_CARD_AMOUNT = 56
        private val deck: Queue<Card> = getShuffledAllCard()

        private fun getShuffledAllCard(): Queue<Card> {
            val totalCards = mutableListOf<Card>().apply {
                for (type in Kinds.values()) {
                    for (shape in Shape.values()) {
                        add(Card(type, shape))
                    }
                }
            }.shuffled()

            return LinkedList<Card>(totalCards)
        }

        fun pop(): Card = deck.poll()
    }
}
