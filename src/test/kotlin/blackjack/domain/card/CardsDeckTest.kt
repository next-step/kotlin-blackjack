package blackjack.domain.card

import blackjack.exception.CardExhaustException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsDeckTest {

    @Test
    fun `카드를 한장 받는다`() {
        val cardsDeck = CardsDeck()
        val card = cardsDeck.divide()

        assertNotNull(card)
    }

    @Test
    fun `카드가 모두 소진되었을때 Exception 이 발생한다`() {
        assertThrows<CardExhaustException> {
            val cardsDeck = CardsDeck()
            val cardCount = 52

            repeat(cardCount + 1) {
                cardsDeck.divide()
            }
        }
    }
}
