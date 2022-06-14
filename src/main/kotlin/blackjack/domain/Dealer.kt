package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Dealer() {
    private val cards: Queue<Card> = LinkedList()

    init {
        Card.pack().shuffled().map { card -> cards.add(card) }
    }

    fun give(): Card {
        return cards.poll()
    }

    fun shareCards(): List<Card> {
        return BASIC_CARD_RANGE.map { cards.poll() }
    }

    fun ask(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    companion object {
        private val BASIC_CARD_RANGE = 1..2
    }
}
