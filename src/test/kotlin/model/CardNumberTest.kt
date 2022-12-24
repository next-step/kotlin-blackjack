package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardNumberTest {
    @Test
    fun `카드 숫자가 문자열로 변환된다`() {
        assertAll(
            {
                assertEquals("K", CardNumber.convertToString(CardNumber.KING))
            },
            {
                assertEquals("5", CardNumber.convertToString(CardNumber.FIVE))
            }
        )
    }
}
