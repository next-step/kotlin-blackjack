package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class Deck {
    val cards: ArrayDeque<Card>

    init {
        val shapes = listOf(CardShape.Spade, CardShape.Diamond, CardShape.Heart, CardShape.Clover)
        val numbers =
            listOf(
                CardNumber.Two,
                CardNumber.Three,
                CardNumber.Four,
                CardNumber.Five,
                CardNumber.Six,
                CardNumber.Seven,
                CardNumber.Eight,
                CardNumber.Nine,
                CardNumber.Ten,
                CardNumber.Jack,
                CardNumber.Queen,
                CardNumber.King,
                CardNumber.Ace,
            )
        cards = ArrayDeque(shapes.flatMap { shape -> numbers.map { number -> Card(shape, number) } }.shuffled())
    }

    fun draw(): Card = cards.removeFirst()

    fun add(card: Card) {
        cards.addFirst(card)
    }

    companion object {
        const val DECK_SIZE = 52
    }
}
