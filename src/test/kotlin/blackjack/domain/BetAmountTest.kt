package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [10000, 23130, 4000000, 100])
    fun `BetAmount는 베팅 금액을 보관한다`(input: Int) {
        assertThat(BetAmount(input).value).isEqualTo(input)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1100, 0, -30, -11])
    fun `0이하의 값이 들어올 경우 IllegalStateException이 발생한다`(input: Int) {
        assertThrows<IllegalArgumentException> { BetAmount(input) }
    }
}
