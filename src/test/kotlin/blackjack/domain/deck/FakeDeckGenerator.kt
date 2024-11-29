package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class FakeDeckGenerator(
    private val shapes: List<CardShape>,
    private val numbers: List<CardNumber>,
) : DeckGenerator {
    override fun generate(
        shapes: List<CardShape>,
        numbers: List<CardNumber>,
    ): Deck {
        return Deck(ArrayDeque(this.shapes.flatMap { shape -> this.numbers.map { number -> Card(shape, number) } }))
    }
}
