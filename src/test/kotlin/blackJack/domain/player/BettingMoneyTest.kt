package blackJack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BettingMoneyTest {
    @Test
    fun `베팅 머니의 초기값은 0이다`() {
        // given
        val bettingMoney = BettingMoney()

        // then
        assertThat(bettingMoney.money).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `돈을 넣는다`() {
        // given
        val bettingMoney = BettingMoney()

        // when
        bettingMoney.inputMoney(BigDecimal(10000))

        // then
        assertThat(bettingMoney.money).isEqualTo(BigDecimal(10000))
    }
}
