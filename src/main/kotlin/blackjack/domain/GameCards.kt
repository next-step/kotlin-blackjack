package blackjack.domain

import java.util.Queue
import java.util.LinkedList

class GameCards(private val cards: Queue<Card> = Card.CARDS.toCollection(LinkedList())) {
    fun poll(): Card {
        return cards.poll() ?: throw IllegalStateException("뽑을 카드가 없습니다.")
    }
}
