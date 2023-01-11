package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class CardNumberTest {
    @Test
    fun `카드 번호가 ACE 인지 확인한다`() {
        assertAll(
            {
                assertThat(CardNumber.ACE.isAce()).isTrue
            },
            {
                assertThat(CardNumber.KING.isAce()).isFalse
            }
        )
    }

    @Test
    fun `카드 번호가 문자열로 변경된다`() {
        assertAll(
            {
                assertThat(CardNumber.ACE.toString()).isEqualTo("A")
            },
            {
                assertThat(CardNumber.KING.toString()).isEqualTo("K")
            },
            {
                assertThat(CardNumber.TWO.toString()).isEqualTo("2")
            }
        )
    }
}
