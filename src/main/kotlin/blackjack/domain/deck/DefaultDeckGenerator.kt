package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class DefaultDeckGenerator : DeckGenerator {
    override fun generate(
        shapes: List<CardShape>,
        numbers: List<CardNumber>,
    ): Deck {
        return Deck(ArrayDeque(shapes.flatMap { shape -> numbers.map { number -> Card(shape, number) } }.shuffled()))
    }
}
