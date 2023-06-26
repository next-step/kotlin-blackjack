package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.Deck

fun deck(
    vararg cards: Card,
) = Deck(
    cards = cards.toMutableList()
)
