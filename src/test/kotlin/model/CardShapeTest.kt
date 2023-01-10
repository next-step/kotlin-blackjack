package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardShapeTest {
    @Test
    fun `카드 모양이 문자열로 변경된다`() {
        assertAll(
            {
                assertThat(CardShape.DIAMONDS.toString()).isEqualTo("다이아몬드")
            },
            {
                assertThat(CardShape.CLUBS.toString()).isEqualTo("클로버")
            }
        )
    }
}
