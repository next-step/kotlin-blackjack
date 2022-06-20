package blackjack.domain.bet

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetTest {
    @Test
    fun `베팅 금액은 0 보다 큰 숫자이다`() {
        Assertions.assertThatNoException().isThrownBy { Bet(0.1) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, -0.1])
    fun `베팅 금액이 0보다 크지 않을 경우 IllegalArgumentException 이 발생한다`(bet: Double) {
        Assertions.assertThatIllegalArgumentException().isThrownBy { Bet(bet) }
    }

//    @Test
//    fun `블랙잭으로 승리했을 경우 베팅 금액의 1.5배의 이익이 발생한다`() {
//
//    }
}
