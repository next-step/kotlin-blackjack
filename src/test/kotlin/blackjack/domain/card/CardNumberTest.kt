package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardNumberTest {

    @Test
    @DisplayName("카드의 숫자가 [ACE, ACE, TWO]인 경우 합산 결과는 [4, 14, 24]")
    fun `When the number of cards is (ACE, ACE, TWO), the total result is (4, 14, 24)`() {
        val numbers = listOf(CardNumber.ACE, CardNumber.ACE, CardNumber.TWO)
        val calculate = CardNumber.calculate(numbers)

        assertThat(calculate).isEqualTo(listOf(4, 14, 24))
    }

    @Test
    @DisplayName("카드의 숫자가 [TEEN, TWO]인 경우 합산 결과는 [12]")
    fun `When the number of cards is (TEEN, TWO), the total result is (12)`() {
        val numbers = listOf(CardNumber.TEEN, CardNumber.TWO)
        val calculate = CardNumber.calculate(numbers)

        assertThat(calculate).isEqualTo(listOf(12))
    }
}
