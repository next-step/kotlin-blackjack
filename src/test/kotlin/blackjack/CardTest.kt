package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.emblem.Spade
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드의 숫자가 1미만일 경우 예외가 발생 한다`() {
        assertThatIllegalArgumentException().isThrownBy { Card(Spade(), 0) }
        assertThatIllegalArgumentException().isThrownBy { Card(Spade(), -1) }
    }

    @Test
    fun `카드의 숫자가 1~13이상일 경우 예외가 발생 하지 않는다`() {
        for (cardNumber in 1..13)
            assertThatNoException().isThrownBy { Card(Spade(), cardNumber) }
    }

    @Test
    fun `카드의 숫자가 14이상일 경우 예외가 발생 한다`() {
        assertThatIllegalArgumentException().isThrownBy { Card(Spade(), 14) }
        assertThatIllegalArgumentException().isThrownBy { Card(Spade(), 15) }
    }
}
