package blackjack.fake

import blackjack.domain.Card
import blackjack.domain.CardGenerator

class FakeCardGenerator(private val card: Card) : CardGenerator {
    override fun generate(): Card {
        return card
    }
}
