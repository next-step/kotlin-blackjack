package blackjack.domain.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

internal class CreditTest {

    @ParameterizedTest
    @ValueSource(ints = [-100, 0, 1000])
    fun `금액을 만들 수 있다`(value: Int) {
        assertThat(Credit.from(value)).isNotNull
    }

    @Test
    fun `금액의 합을 추가하면 추가된 값을 리턴한다`() {
        val givenCredit = Credit.from(100)

        val actual = givenCredit.add(100)

        assertThat(actual.value).isEqualTo(BigDecimal.valueOf(200))
    }

    @Test
    fun `금액을 차감하면 차감된 값을 리턴한다`() {
        val givenCredit = Credit.from(1000)

        val actual = givenCredit.subtract(500)

        assertThat(actual.value).isEqualTo(BigDecimal.valueOf(500))
    }

    @Test
    fun `금액을 주어진 값으로 곱한 값을 리턴한다`() {
        val givenCredit = Credit.from(1000)

        val actual = givenCredit.multiply(1.5)

        assertThat(actual.value).isEqualTo(BigDecimal.valueOf(1500.0))
    }
}
