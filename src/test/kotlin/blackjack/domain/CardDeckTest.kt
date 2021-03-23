package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    private val MAX_CARD_DECK = 52

    @Test
    fun `카드덱이 모두 소진됐을 때 또 드로우를 하면 Exception 발생`() {
        repeat(MAX_CARD_DECK) {
            CardDeck.drawCard()
        }
        assertThrows<IllegalStateException> { CardDeck.drawCard() }
    }
}
