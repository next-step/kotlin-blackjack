package blackJack

import CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CardNumberTest {

    @Test
    fun `트럼프 카드에 범위를 넘어서는 번호가 있다면, 트럼프 카드 번호를 생성할 때, 예외를 던진다`() {
        // given :  트럼프 카드의 범위를 넘어서는 번호가 있다.
        val cardNumber = "10"

        // when : 트럼프 카드의 번호를 생성한다.
        val cardSuit = runCatching { CardNumber.from(cardNumber) }.exceptionOrNull()

        // then : 예외를 던진다.
        Assertions.assertThat(cardSuit).isInstanceOf(IllegalArgumentException::class.java)
    }
}
