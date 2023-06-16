package blackjack.domain.deck

import blackjack.domain.card.Card

@JvmInline
value class Deck(
    private val cards: ArrayDeque<Card> = DeckGenerator.generator(),
)
