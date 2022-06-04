package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Deck(cards: List<Card>) {
    private val cards: Queue<Card> = LinkedList(cards)

    val count: Int
        get() = cards.size

    fun draw(): Card = cards.poll() ?: throw IllegalStateException("남아있는 카드가 없습니다")

    companion object {

        fun shuffled(): Deck {
            TODO()
        }
    }
}
