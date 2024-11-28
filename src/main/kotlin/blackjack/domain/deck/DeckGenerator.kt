package blackjack.domain.deck

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardNumber.Companion.NUMBER_CACHE
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardShape.Companion.SHAPE_CACHE

interface DeckGenerator {
    fun generate(
        shapes: List<CardShape> = SHAPE_CACHE,
        numbers: List<CardNumber> = NUMBER_CACHE,
    ): Deck
}
