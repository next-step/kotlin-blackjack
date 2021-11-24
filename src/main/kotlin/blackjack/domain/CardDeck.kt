package blackjack.domain

import blackjack.domain.Card.Companion.CARD_SET
import java.util.LinkedList
import java.util.Queue

data class CardDeck(private val cardQueue: Queue<Card> = LinkedList(CARD_SET.shuffled())) : Queue<Card> by cardQueue {

    fun next(): Card {
        if (cardQueue.isEmpty()) {
            resetQueue()
        }
        return cardQueue.poll()
    }

    private fun resetQueue() {
        cardQueue.clear()
        cardQueue.addAll(CARD_SET.shuffled())
    }
}
