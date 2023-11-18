package blackjack.entity

import blackjack.domain.Card

data class Cards(
    val cards: ArrayDeque<Card>
) : List<Card> by cards {
    val cardsContainACard: Boolean
        get() {
            return cards.any { card -> card.number == CardNumber.A }
        }

    val sumOfCards: Int
        get() = cards.sumOf { it.number.number }

    fun sumOfCardNumbers(): Int = if (cardsContainACard) {
        sumOfCardsWithA()
    } else {
        sumOfCards
    }

    /**
     * A가 가질 수 있는 상태가 1, 11이지만 선택은 참여자가 선택한다고 생각해서 이곳에 함수를 만들었습니다.
     */
    private fun sumOfCardsWithA(): Int = if (sumOfCards + SUPER_A_PLUS_NUMBER > BLACK_JACK) {
        sumOfCards
    } else {
        sumOfCards + SUPER_A_PLUS_NUMBER
    }

    fun addNewCard(card: Cards): Cards {
        val cards = (cards + card.cards)
        return cards.toCards()
    }

    companion object {
        private const val BLACK_JACK = 21
        private const val SUPER_A_PLUS_NUMBER = 10
        fun createCardDeque(): Cards {
            val cardNumbers = CardNumber.values()
            val cardShapes = CardShape.values()
            return cardNumbers.flatMap { cardNumber ->
                cardShapes.map { cardShape -> Card(number = cardNumber, shape = cardShape) }
            }.shuffled().toCards()
        }
    }
}

fun List<Card>.toCards() = Cards(ArrayDeque(this))
