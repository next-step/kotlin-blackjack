package blackjack.domain.game

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GameResultTest {

    @Test
    fun `승리할 경우 수익은 배팅한 금액만큼이다`() {
        val bet = Money(1000)
        val result = GameResult.Type.WIN

        val profit = result.calculateProfit(bet)

        assertThat(profit.value).isEqualTo(1000)
    }

    @Test
    fun `비길 경우 수익은 0이다`() {
        val bet = Money(1000)
        val result = GameResult.Type.DRAW

        val profit = result.calculateProfit(bet)

        assertThat(profit.value).isEqualTo(0)
    }

    @Test
    fun `질 경우 수익은 배팅한 금액만큼 잃는다`() {
        val bet = Money(1000)
        val result = GameResult.Type.LOSE

        val profit = result.calculateProfit(bet)

        assertThat(profit.value).isEqualTo(-1000)
    }

    @Test
    fun `블랙잭일 경우 수익은 배팅한 금액의 반이다`() {
        val bet = Money(1000)
        val result = GameResult.Type.BLACK_JACK

        val profit = result.calculateProfit(bet)

        assertThat(profit.value).isEqualTo(500)
    }
}
