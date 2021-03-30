package blackjack.domain.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    @Test
    fun `카드를 52번 드로우 한 뒤 또 드로우를 하면 Exception 발생`() {
        repeat(52) {
            CardDeck.drawCard()
        }
        assertThrows<NoSuchElementException> { CardDeck.drawCard() }
    }
}
