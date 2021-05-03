package blackjack.domain

import java.util.Queue
import java.util.LinkedList

class GameCards(private val cards: Queue<Card> = Card.CARDS.toCollection(LinkedList())) {
    fun poll(): Card {
        return cards.poll() ?: throw EMPTY_CARDS_EXCEPTION
    }

    fun nextCard(): Card {
        return cards.peek() ?: throw EMPTY_CARDS_EXCEPTION
    }

    fun removeFront() {
        cards.remove()
    }

    companion object {
        private val EMPTY_CARDS_EXCEPTION = IllegalStateException("뽑을 카드가 없습니다.")
    }
}
