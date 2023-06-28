package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Dealer private constructor(val cards: Queue<Card>) {
    fun open(): Pair<Card, Card> {
        return Pair(cards.poll(), cards.poll())
    }
    fun dealing(): Card {
        return cards.poll()
    }
    companion object {

        const val CARD_SET_SIZE = 52
        fun create(): Dealer {
            val cardShapes = CardShape.values().toList()
            val cardNumbers = CardNumber.values().toList()

            val cards: List<Card> = cardShapes
                .map { cardShape -> cardNumbers.map { cardNumber -> Card(cardShape, cardNumber) } }
                .flatten()
                .toSet()
                .shuffled()

            require(cards.size == CARD_SET_SIZE) { println("게임을 진행 하기 위해선 총 ${CARD_SET_SIZE}장의 카드가 필요 합니다.") }

            return Dealer(LinkedList(cards))
        }
    }
}

class BurstPlayerCanNotHitException: RuntimeException("버스트(BURST) 상태에서 더 이상 카드를 받을 수 없습니다.")
