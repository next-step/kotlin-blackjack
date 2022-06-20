package blackjack.domain.bet

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetTest {
    @Test
    fun `베팅 금액은 0 보다 큰 숫자이다`() {
        assertThatNoException().isThrownBy { Bet(0.1) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, -0.1])
    fun `베팅 금액이 0보다 크지 않을 경우 IllegalArgumentException 이 발생한다`(bet: Double) {
        assertThatIllegalArgumentException().isThrownBy { Bet(bet) }
    }

    @Test
    fun `블랙잭으로 승리했을 경우 베팅 금액의 1,5배의 이익이 발생한다`() {
        val bet = 1000.0
        assertThat(Bet(bet).blackjackWin().value).isEqualTo(bet * 1.5)
    }

    @Test
    fun `블랙잭이 아닌 승리일 경우 베팅 금액만큼의 이익이 발생한다`() {
        val bet = 1000.0
        assertThat(Bet(bet).win().value).isEqualTo(bet)
    }

    @Test
    fun `푸시가 발생할 경우(무승부) 총 이익은 0 이다`() {
        assertThat(Bet(1000.0).push().value).isEqualTo(0.0)
    }

    @Test
    fun `질 경우 베팅 금액을 잃는다`() {
        val bet = 1000.0
        assertThat(Bet(bet).lose().value).isEqualTo(-bet)
    }
}
