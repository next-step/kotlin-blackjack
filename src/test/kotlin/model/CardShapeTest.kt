package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardShapeTest {
    @Test
    fun `카드 모양이 한글 문자열로 변환된다`() {
        assertAll(
            {
                assertEquals("다이아몬드", CardShape.convertToString(CardShape.DIAMONDS))
            },
            {
                assertEquals("스페이드", CardShape.convertToString(CardShape.SPADES))
            }
        )
    }
}
