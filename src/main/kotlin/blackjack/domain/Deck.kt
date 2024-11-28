package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import java.util.LinkedList
import java.util.Queue

class Deck {
    val cards: Queue<Card>

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
        cards = LinkedList(shapes.flatMap { shape -> numbers.map { number -> Card(shape, number) } }.shuffled())
    }

    fun draw(): Card = cards.poll()

    companion object {
        const val DECK_SIZE = 52
    }
}
