package blackjack.test

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

object FakeGenerator {
    fun card(number: CardNumber): Card {
        return Card(number, CardShape.values().random())
    }

    fun card(): Card {
        return Card(
            CardNumber.values().random(),
            CardShape.values().random()
        )
    }
}
