package blackjack.domain.game

import blackjack.domain.game.Profit.Companion.sum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class ProfitTest {

    @Test
    fun `실수값 수익은 소수점 아래를 버린다`() {
        val profit = Profit(1000.1)

        assertThat(profit.value).isEqualTo(1000)
    }

    @Test
    fun `수익의 음수를 구한다`() {
        val profit = Profit(1000)

        val result = profit.negative()

        assertThat(result.value).isEqualTo(-1000)
    }

    @Test
    fun `수익의 합을 구한다`() {
        val profits = listOf(Profit(1000), Profit(2000), Profit(3000))

        val result = profits.sum()

        assertThat(result.value).isEqualTo(6000)
    }
}
