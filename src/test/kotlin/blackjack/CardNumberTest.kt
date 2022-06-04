package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class CardNumberTest {
    @Test
    fun `1에서13이하의 카드 숫자 객체를 생성`() {
        val cardNumber = CardNumber(1)

        assertThat(cardNumber).isEqualTo(CardNumber(1))
    }

    @Test
    fun `1 미만 카드 숫자 일 시 예외처리`() {
        assertThatIllegalArgumentException()
            .isThrownBy { CardNumber(0) }
    }

    @Test
    fun `13 초과 카드 숫자 일 시 예외처리`() {
        assertThatIllegalArgumentException()
            .isThrownBy { CardNumber(14) }
    }
}
