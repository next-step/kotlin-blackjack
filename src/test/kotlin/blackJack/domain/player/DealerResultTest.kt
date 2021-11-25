package blackJack.domain.player

import blackJack.domain.result.BettingResult
import blackJack.domain.result.DealerResult
import blackJack.domain.result.PlayerResult
import blackJack.domain.result.PlayerResults
import blackJack.domain.result.WinDrawLose
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerResultTest {
    @Test
    fun `플레이어 4명 모두 만원씩 배팅한 상태를 합쳐서 2승 1패 1무이면, 딜러의 이익은 -10000이다`() {
        // given
        val playerResults = PlayerResults(
            listOf(
                PlayerResult("flamme", winDrawLose = WinDrawLose.WIN, BettingResult(10000)),
                PlayerResult("chacha", winDrawLose = WinDrawLose.WIN, BettingResult(10000)),
                PlayerResult("rain", winDrawLose = WinDrawLose.LOSE, BettingResult(10000)),
                PlayerResult("yong", winDrawLose = WinDrawLose.DRAW, BettingResult(10000))
            )
        )

        // when
        val dealerProfit = DealerResult.winOrLose(playerResults).profit

        // then
        assertThat(dealerProfit).isEqualTo(-10000)
    }
}
