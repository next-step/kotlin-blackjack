package blackjack.domain.bet

import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ProfitTest {
    @ParameterizedTest
    @ValueSource(doubles = [-10.5, 0.0, 10.5])
    fun `베팅 이익은 음수, 0, 양수가 될 수 있다`(profit: Double) {
        assertThatNoException().isThrownBy { Profit(profit) }
    }
}
