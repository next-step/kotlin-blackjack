package blackjack.domain.card

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardDenominationTest {
    @Test
    fun `getValue 시 자신이 가진 value 를 반환한다`() {
        val actual = CardDenomination.KING.getValue(0)

        assertEquals(10, actual)
    }

    @Test
    fun `현재까지의 숫자 합이 10이 넘어갈경우 1을 반환한다`() {
        val actual = CardDenomination.ACE.getValue(11)

        assertEquals(1, actual)
    }

    @Test
    fun `현재까지의 숫자 합이 10 이하일경우 11을 반환한다`() {
        val actual = CardDenomination.ACE.getValue(10)

        assertEquals(11, actual)
    }
}
