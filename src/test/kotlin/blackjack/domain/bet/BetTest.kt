package blackjack.domain.bet

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetTest {
    @Test
    fun `베팅 금액은 0 보다 큰 숫자이다`() {
        Assertions.assertThatNoException().isThrownBy { Bet(1) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `베팅 금액이 0보다 크지 않을 경우 IllegalArgumentException 이 발생한다`(bet: Int) {
        Assertions.assertThatIllegalArgumentException().isThrownBy { Bet(bet) }
    }
}
