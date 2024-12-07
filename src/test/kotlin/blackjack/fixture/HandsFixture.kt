package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.Hands

internal fun handsFixture(vararg values: Card): Hands {
    val hands = Hands()
    values.forEach(hands::add)
    return hands
}
