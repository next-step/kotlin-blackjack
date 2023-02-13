package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProfitCalculatorTest {
    private val player: Player = Player("Tester")

    @Test
    fun `게임에 이겼을 경우 베팅 금액을 수익으로 가진다`() {
        player.updateBettingMoney(100)
        player.updateGameResult(GameResultState.WIN)
        assertThat(ProfitCalculator().getProfitResult(player)).isEqualTo(100)
    }

    @Test
    fun `게임에 졌을 경우 베팅 금액만큼 수익을 잃는다`() {
        player.updateBettingMoney(200)
        player.updateGameResult(GameResultState.LOSE)
        assertThat(ProfitCalculator().getProfitResult(player)).isEqualTo(-200)
    }

    @Test
    fun `게임에 비겼을 경우 수익은 0이다`() {
        player.updateBettingMoney(50000)
        player.updateGameResult(GameResultState.DRAW)
        assertThat(ProfitCalculator().getProfitResult(player)).isEqualTo(0)
    }

    @Test
    fun `처음 두 장의 카드 합이 21로 블랙잭일 경우 수익은 베팅 금액의 150%이다`() {
        player.updateBettingMoney(10000)
        player.receiveCard(Card(CardNumber.ACE, CardShape.SPADES))
        player.receiveCard(Card(CardNumber.KING, CardShape.HEARTS))
        player.updateGameResult(GameResultState.WIN)
        assertThat(ProfitCalculator().getProfitResult(player)).isEqualTo(15000)
    }
}
