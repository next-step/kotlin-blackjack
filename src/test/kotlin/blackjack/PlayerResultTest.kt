package blackjack

import blackjack.domain.player.Bet
import blackjack.domain.player.BetResult
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.Status
import blackjack.domain.player.WinStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {
    private val playerWin = 4
    private val playerLose = 5
    private val playerName = PlayerName("Player")
    private val playerResult =
        PlayerResult(
            BetResult(1000),
            Status(
                playerName,
                Bet(1000),
                WinStatus(playerWin, playerLose)
            )
        )

    @Test
    fun `playerResult에 설정 된 승리 결과를 확인 할 수 있다`() {
        val actualWinResult = playerResult.playerStatus.getWin()
        assertThat(actualWinResult).isEqualTo(playerWin)
        val actualLoseResult = playerResult.playerStatus.getLose()
        assertThat(actualLoseResult).isEqualTo(playerLose)
    }
    @Test
    fun `playerResult에서 설정 된 UserName을 확인 할 수 있다`() {
        val actualResult = playerResult.getName()
        assertThat(actualResult).isEqualTo(playerName)
    }
}
