package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드는 모양과 숫자로 이루어져있다`() {
        val card = Card(Denomination.TWO, Suit.CLUBS)
        assertEquals(card.toString(), "2클로버")
    }
}
