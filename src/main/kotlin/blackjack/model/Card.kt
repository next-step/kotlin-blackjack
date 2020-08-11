package blackjack.model

import java.util.LinkedList
import java.util.Queue

class Card(private val kinds: Kinds, private val shape: Shape) {

    override fun toString(): String = "${kinds.kindsName}${shape.shapeName}"

    fun getCardKinds() = Kinds.findByKinds(kinds)

    companion object {
        const val TOTAL_CARD_AMOUNT = 52
        private val deck: Queue<Card> = getShuffledAllCard()

        private fun getShuffledAllCard(): Queue<Card> {
            val totalCards = mutableListOf<Card>()
                .apply { setupKinds() }
                .shuffled()

            return LinkedList<Card>(totalCards)
        }

        private fun MutableList<Card>.setupKinds() {
            for (type in Kinds.values()) {
                setupShape(type)
            }
        }

        private fun MutableList<Card>.setupShape(type: Kinds) {
            for (shape in Shape.values()) {
                add(Card(type, shape))
            }
        }

        fun pop(): Card = deck.poll()
    }
}
