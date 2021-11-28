package blackject

import blackject.model.Amount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountTest {
    @Test
    @DisplayName("0이상의 금액을 배팅할 수 있다.")
    fun `check amount that must over 0`() {
        val money = 1000.0

        val amount = Amount(money)

        Assertions.assertThat(amount.value).isEqualTo(money)
    }

    @Test
    @DisplayName("0보다 적은 금액을 배팅할 수 없다.")
    fun `check amount that must below 0`() {
        val money = -1.0

        assertThrows<IllegalArgumentException> {
            Amount(money)
        }
    }
}
