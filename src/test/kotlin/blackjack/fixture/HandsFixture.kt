package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.Hands

internal fun handsFixture(
    values: List<Card> =
        listOf(
            cardFixture(),
        ),
): Hands {
    val hands = Hands()
    values.forEach(hands::add)
    return hands
}
