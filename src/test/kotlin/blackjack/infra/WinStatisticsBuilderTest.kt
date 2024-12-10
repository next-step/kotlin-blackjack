package blackjack.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinStatisticsBuilderTest{
    @Test
    fun `win lose draw 를 설정 할 수 있다`() {
        val winStatisticsBuilder = WinStatisticsBuilder()
        winStatisticsBuilder.onWin()
        winStatisticsBuilder.onWin()
        winStatisticsBuilder.onLose()
        winStatisticsBuilder.onDraw()

        val actual = winStatisticsBuilder.build()

        assertAll(
            { assertThat(actual.wins).isEqualTo(2) },
            { assertThat(actual.losses).isEqualTo(1) },
            { assertThat(actual.draws).isEqualTo(1) }
        )
    }

}