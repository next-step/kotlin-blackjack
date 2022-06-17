package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Dealer() : Player("딜러") {
    private val cardPack: Queue<Card> = LinkedList()
    override val cards = mutableListOf<Card>()

    init {
        Card.pack().shuffled().map { card -> cardPack.add(card) }
    }

    fun give(): Card {
        return cardPack.poll()
    }

    fun shareCards(): List<Card> {
        return BASIC_CARD_RANGE.map { cardPack.poll() }
    }

    fun ask(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    companion object {
        private val BASIC_CARD_RANGE = 1..2
    }
}
