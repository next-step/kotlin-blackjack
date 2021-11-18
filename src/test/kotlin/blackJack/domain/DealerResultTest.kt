package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerResultTest {
    @Test
    fun `플레이어 4명의 상태를 합쳐서 2승 1패 1무이면, 딜러의 승패는  1승 2패 1무이다`() {
        // given
        val playerResults = PlayerResults(
            listOf(
                PlayerResult("flamme", winDrawLose = WinDrawLose.WIN),
                PlayerResult("chacha", winDrawLose = WinDrawLose.WIN),
                PlayerResult("rain", winDrawLose = WinDrawLose.LOSE),
                PlayerResult("yong", winDrawLose = WinDrawLose.DRAW)
            )
        )

        // when
        val dealerResult = DealerResult.winOrLose(playerResults)

        // then
        assertAll({
            assertThat(dealerResult.win).isEqualTo(1)
            assertThat(dealerResult.lose).isEqualTo(2)
            assertThat(dealerResult.draw).isEqualTo(1)
        })
    }
}
