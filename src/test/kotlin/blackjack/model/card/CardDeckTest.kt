package blackjack.model.card

import blackjack.model.strategy.NormalStrategy
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 덱에서 카드 한장을 받는다`() {
        val cardDeck = CardDeck(NormalStrategy())
        val result = cardDeck.draw()

        result shouldBe Card(CardNumber.ACE, CardSuit.다이아몬드)
    }
}
