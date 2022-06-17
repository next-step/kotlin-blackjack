package blackjack.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardTest {

    @Test
    fun createCard() {
        assertDoesNotThrow { Card(Suit.CLUB, CardSymbol.ONE) }
    }
}
