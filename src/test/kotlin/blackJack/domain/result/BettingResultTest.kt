package blackJack.domain.result

import blackJack.domain.card.BlackJack
import blackJack.domain.card.Cards
import blackJack.domain.player.Player
import blackJack.domain.player.StateImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BettingResultTest {

    @Test
    fun `플레이어가 이긴 상태인 경우 수익금 비교`() {
        // given
        val player = Player.of("name").apply {
            this.bet(10000)
        }
        val bettingResult = BettingResult.of(player, WinDrawLose.WIN)

        // when
        val profit = bettingResult.profit

        // then
        assertThat(profit).isEqualTo(10000)
    }

    @Test
    fun `플레이어가 무승부 상태인 경우 수익금 비교`() {
        // given
        val player = Player.of("name").apply {
            this.bet(10000)
        }
        val bettingResult = BettingResult.of(player, WinDrawLose.DRAW)

        // when
        val profit = bettingResult.profit

        // then
        assertThat(profit).isEqualTo(0)
    }

    @Test
    fun `플레이어가 진 상태인 경우 수익금 비교`() {
        // given
        val player = Player.of("name").apply {
            this.bet(10000)
        }
        val bettingResult = BettingResult.of(player, WinDrawLose.LOSE)

        // when
        val profit = bettingResult.profit

        // then
        assertThat(profit).isEqualTo(-10000)
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 상태는 배팅 금액의 1.5배를 해준다.")
    fun playerBlackJackProfitTest() {
        // given
        val player = Player("name", StateImpl(Cards(listOf()), BlackJack)).apply {
            this.bet(10000)
        }
        val bettingResult = BettingResult.of(player, WinDrawLose.WIN)

        // when
        val profit = bettingResult.profit

        // then
        assertThat(profit).isEqualTo(15000)
    }
}
