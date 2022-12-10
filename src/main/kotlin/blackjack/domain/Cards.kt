package blackjack.domain

import java.util.Stack

class Cards(val cardStack: Stack<Card>) {

    constructor(cards: List<Card>) : this(
        cardStack = cards.toCollection(Stack())
    )

    init {
        require(cardStack.size >= INIT_COUNT) { "최초 카드 ${INIT_COUNT}장 이상이여야 합니다." }
    }

    fun add(card: Card) {
        cardStack.push(card.copy())
    }

    fun point(): Int =
        cardStack.fold(0) { acc, card ->
            val totalPoint = acc + card.number.value

            if (card.number.isAce()) {
                val maxValue = acc + card.number.orValue
                val cardPoint = if (maxValue < BLACK_JACk_NUMBER) card.number.orValue else card.number.value

                return@fold acc + cardPoint
            }

            totalPoint
        }

    companion object {
        const val BLACK_JACk_NUMBER = 21
        const val INIT_COUNT = 2
    }
}
