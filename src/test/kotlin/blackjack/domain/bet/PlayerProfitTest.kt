package blackjack.domain.bet

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerProfitTest {
    @Test
    fun `플레이어의 이름을 가지고 있다`() {
        val playerName = "vivian"
        assertThat(PlayerProfit(playerName, Profit(1.0)).playerName).isEqualTo(playerName)
    }

    @Test
    fun `플레이어의 베팅 수익을 가지고 있다`() {
        val profit = Profit(1.0)
        assertThat(PlayerProfit("vivian", Profit(1.0)).profit).isEqualTo(profit)
    }
}
