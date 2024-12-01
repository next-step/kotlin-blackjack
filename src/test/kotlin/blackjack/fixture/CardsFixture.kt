package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.Cards

internal fun cardsFixture(
    values: List<Card> = listOf(
        cardFixture(),
    ),
): Cards {
    val cards = Cards.emptyCards()
    values.forEach(cards::add)
    return cards
}


