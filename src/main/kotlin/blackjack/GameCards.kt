package blackjack

import java.util.*

class GameCards (private val cards: Queue<Card> = Card.ALL_CARDS.toCollection(LinkedList())) {
    fun poll(): Card {
        return cards.poll() ?: throw IllegalStateException("뽑을 카드가 없습니다.")
    }
}
