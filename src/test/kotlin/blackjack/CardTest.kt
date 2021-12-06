package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.suit.SuitTypes.Spade
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @Test
    fun `카드의 숫자가 1미만일 경우 예외가 발생 한다`() {
        assertThatIllegalArgumentException().isThrownBy { Card(Spade, 0) }
        assertThatIllegalArgumentException().isThrownBy { Card(Spade, -1) }
    }

    @Test
    fun `카드의 숫자가 1~13이상일 경우 예외가 발생 하지 않는다`() {
        for (cardNumber in 1..13)
            assertThatNoException().isThrownBy { Card(Spade, cardNumber) }
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13])
    fun `카드의 숫자가 11이상일 경우 카드의 값이 10으로 고정 된다`(cardNumber: Int) {
        val expectedValue = 10
        val actualValue = Card(Spade, cardNumber).cardValue
        assertThat(actualValue).isEqualTo(expectedValue)
    }

    @Test
    fun `카드의 숫자가 11이상일 경우 카드의 이름이 J, Q, K로 변경 된다`() {
        val cardJ = Card(Spade, 11)
        val cardQ = Card(Spade, 12)
        val cardK = Card(Spade, 13)

        assertThat(cardJ.cardName).isEqualTo("J")
        assertThat(cardQ.cardName).isEqualTo("Q")
        assertThat(cardK.cardName).isEqualTo("K")
    }

    @Test
    fun `카드의 숫자가 14이상일 경우 예외가 발생 한다`() {
        assertThatIllegalArgumentException().isThrownBy { Card(Spade, 14) }
        assertThatIllegalArgumentException().isThrownBy { Card(Spade, 15) }
    }
}
