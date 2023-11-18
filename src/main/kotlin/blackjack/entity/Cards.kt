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

    fun addNewCard(card: Cards): Cards {
        val cards = (cards + card.cards)
        return cards.toCards()
    }

    companion object {
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
