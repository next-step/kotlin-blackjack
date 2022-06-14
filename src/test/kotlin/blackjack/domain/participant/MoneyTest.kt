package blackjack.domain.participant

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class MoneyTest {

    @Test
    fun `베팅 금액이 0 이하인 경우 예외가 발생한다`() {
        assertThatThrownBy { Money(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("베팅 금액은 0원 이상이어야 합니다.")
    }

    @Test
    fun `베팅 금액에서 수익을 합산하여 최종 수익을 계산할 수 있다`() {
        // given
        val bat = 10000
        val profitPercentage = 1.5
        val winMoney = (bat * profitPercentage).toInt()
        val money = Money(bat)

        // when
        money.accBatMoney(winMoney)

        // then
        assertThat(money.bat).isEqualTo(bat + winMoney)
    }
}
