package blackjack.model

import java.util.LinkedList
import java.util.Queue

class Deck(private val type: Card.Type, private val shape: Card.Shape) {

    override fun toString(): String = "${type.nickName}${shape.nickName}"

    fun getDeckType(): Card.Type = Card.Type.findByNickname(type.nickName)

    companion object {
        const val TOTAL_DECK_SIZE = 56
        private val all: Queue<Deck> = getShuffledAllDeck()

        private fun getShuffledAllDeck(): Queue<Deck> {
            val totalDeck = mutableListOf<Deck>().apply {
                for (type in Card.Type.values()) {
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
