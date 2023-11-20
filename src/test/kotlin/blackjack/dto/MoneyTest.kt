package blackjack.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoneyTest {

    @ParameterizedTest
    @CsvSource(value = ["1000, 2, 2000", "1000, 0.5, 500"])
    fun `곱하기가 가능하다`(money: Int, times: Double, expected: Int) {
        val result = Money(money) * times
        assertThat(result.money).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["1000, 2, 998", "1000, -500, 1500"])
    fun `빼기가 가능하다`(money: Int, minus: Int, expected: Int) {
        val result = Money(money) - Money(minus)
        assertThat(result.money).isEqualTo(expected)
    }
}
