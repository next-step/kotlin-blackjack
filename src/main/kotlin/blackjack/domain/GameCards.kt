package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class GameCards(cards: Set<Card> = Card.CARDS) {
    private val _cards: Queue<Card> = cards.toCollection(LinkedList())

    fun poll(): Card {
        return _cards.poll() ?: throw IllegalStateException("뽑을 카드가 없습니다.")
    }

    fun pollCardsToFirstDraw() = (Game.START_INDEX..Game.BLACK_JACK_CARD_COUNT).map { _cards.poll() }.toSet()
}
