package blackjack_dealer.entity

import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape

class CardDeque(
    private val cardDeque: ArrayDeque<Card> = ArrayDeque()
) : List<Card> by cardDeque {
    fun create(): CardDeque {
        val cardNumber = CardNumber.values()
        val cardShapes = CardShape.values()
        val createCardDeque = cardNumber.flatMap { number ->
            cardShapes.map { shape ->
                Card(
                    cardNumber = number,
                    cardShape = shape
                )
            }
        }.shuffled()
        return CardDeque(ArrayDeque(createCardDeque))
    }

    fun generateSingleCard(): Card {
        return kotlin.runCatching {
            cardDeque.removeLast()
        }.getOrNull() ?: error("카드를 모두 나누어주었습니다.")
    }

    fun generateDoubleCard(): GamerCards {
        val initialFirstCard = generateSingleCard()
        val initialSecondCard = generateSingleCard()
        return listOf(initialFirstCard, initialSecondCard).toGamerCards()
    }
}
