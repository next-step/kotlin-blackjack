package blackjack_dealer.entity

import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape

class CardDeque {
    private var cardDeque: ArrayDeque<Card> = ArrayDeque()

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
        return CardDeque().apply {
            cardDeque = ArrayDeque(createCardDeque)
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

    /**
     * 아래 함수들은 테스트 클래스에서만 사용됩니다
     */
    fun removeCustomCard(card: Card) {
        cardDeque.removeIf { it == card }
    }

    fun getRemainCardsCount(): Int {
        return cardDeque.count()
    }

    fun containCardRemainCards(card: Card): Boolean {
        return cardDeque.contains(card)
    }
}
