package domain.factory

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape

interface CardsFactory {
    fun generate(): MutableList<Card>
}

object DefaultCardsFactory : CardsFactory {
    override fun generate(): MutableList<Card> =
        CardShape.values().flatMap { shape ->
            CardNumber.values().map { number ->
                Card(shape, number)
            }
        }.toMutableList()
}
