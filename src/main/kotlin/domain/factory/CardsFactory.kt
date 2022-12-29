package domain.factory

import domain.Card
import domain.CardNumber
import domain.CardShape

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
