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
    fun `블랙잭 게임 결과의 수익을 퍼센테이지로 관리한다`() {
        // given
        val money = Money(10000)

        // when
        money.setProfitPercentage(150)

        // then
        assertThat(money.profitPercentage).isEqualTo(150)
    }

    @Test
    fun `베팅 금액과 수익으로 최종 수익을 계산할 수 있다`() {
        // given
        val bat = 10000
        val profitPercentage = 150
        val expectedFinalEarnings = 15000

        // when
        val money = Money(bat)
        money.setProfitPercentage(profitPercentage)
        val result = money.getFinalEarnings()

        // then
        assertThat(result).isEqualTo(expectedFinalEarnings)
    }
}
