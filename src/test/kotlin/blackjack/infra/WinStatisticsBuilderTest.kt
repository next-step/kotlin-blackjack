package blackjack.infra

import blackjack.domain.Money
import blackjack.domain.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinStatisticsBuilderTest {
    @Test
    fun `win lose draw 를 설정 할 수 있다`() {
        val winStatisticsBuilder = WinStatisticsBuilder()
        val player = Player.from("player")
        player.betting(Money(1000))
        winStatisticsBuilder.onWin(player)
        winStatisticsBuilder.onWin(player)
        winStatisticsBuilder.onLose(player)
        winStatisticsBuilder.onDraw(player)

        val actual = winStatisticsBuilder.build()

        assertAll(
            { assertThat(actual.getStats("player").wins).isEqualTo(2) },
            { assertThat(actual.getStats("player").losses).isEqualTo(1) },
            { assertThat(actual.getStats("player").draws).isEqualTo(1) },
        )
    }
}
