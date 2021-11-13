package blackject

import blackject.model.card.CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardNumberTest {
    @Test
    @DisplayName("Ace 카드의 최소 숫자 구하기")
    fun `check ace number of min`() {
        val ace = CardNumber.ACE
        val expectedMinNumber = 1

        val number = CardNumber.getNumberValue(ace)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("Ace 카드의 최대 숫자 구하기")
    fun `check ace number of max`() {
        val ace = CardNumber.ACE
        val expectedMinNumber = 11

        val number = CardNumber.getNumberValue(ace, true)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("킹 카드의 숫자 확인")
    fun `check king card number`() {
        val king = CardNumber.KING
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("잭 카드의 숫자 확인")
    fun `check jack card number`() {
        val king = CardNumber.JACK
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }

    @Test
    @DisplayName("퀸 카드의 숫자 확인")
    fun `check queen card number`() {
        val king = CardNumber.QUEEN
        val expectedMinNumber = 10

        val number = CardNumber.getNumberValue(king)

        Assertions.assertThat(number).isEqualTo(expectedMinNumber)
    }
}
