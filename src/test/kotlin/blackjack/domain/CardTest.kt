package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `Card 문자열 변환 테스트`() {
        // given
        val denomination = Denomination.ACE
        val suit = Suit.SPADES
        val expected = "A스페이드"

        // when
        val cardString = Card(denomination = denomination, suit = suit).toString()

        // then
        assertEquals(expected, cardString)
    }
}
