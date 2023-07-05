package blackjack.testdouble

import blackjack.domain.Card
import blackjack.domain.CardGenerator

class StubCardGenerator(private val card: Card) : CardGenerator {
    override fun generate(): Card {
        return card
    }
}
