package blackjack_dealer.entity

import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape

object CardDeque {
    private var _cardDeque: ArrayDeque<Card> = ArrayDeque()
    val cardDeque get() = _cardDeque

    fun create(): CardDeque {
        val cardNumber = CardNumber.values()
        val cardShapes = CardShape.values()
        val cardDeque = cardNumber.flatMap { number ->
            cardShapes.map { shape ->
                Card(
                    cardNumber = number,
                    cardShape = shape
                )
            }
        }.shuffled()
        return CardDeque.apply {
            _cardDeque = ArrayDeque(cardDeque)
        }
    }

    fun generateSingleCard(): Card {
        return cardDeque.removeLast()
    }

    fun generateDoubleCard(): GamerCards {
        val initialFirstCard = generateSingleCard()
        val initialSecondCard = generateSingleCard()
        return mutableListOf(initialFirstCard, initialSecondCard).toGamerCards()
    }
}
