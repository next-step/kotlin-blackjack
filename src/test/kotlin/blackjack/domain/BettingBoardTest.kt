package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingBoardTest {
    private val testPlayers = listOf(
        Player("test1"),
        Player("test2"),
        Player("test3"),
    )

    @ParameterizedTest
    @ValueSource(ints = [2000, 5000])
    fun `베팅 금액을 반환한다`(money: Int) {
        val player = testPlayers.first()
        val bettingBoard = BettingBoard(
            mapOf(player.name to money)
        )

        assertThat(bettingBoard.betOf(player.name)).isEqualTo(money)
    }

    @Test
    fun `존재하지 않는 플레이어의 베팅 금액을 조회할 경우 예외를 던진다`() {
        val unknownUser = "unknownUser1"
        val bettingBoard = BettingBoard(
            mapOf(testPlayers.first().name to 1000)
        )

        assertThatThrownBy { bettingBoard.betOf(unknownUser) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("존재하지 않는 플레이어입니다.")
    }

    @Test
    fun `승리한 플레이어에게 상금을 지급하고 보유 금액을 차감한다`() {
        val testMoney = 3000
        val bettingBoard = BettingBoard(
            mapOf(testPlayers.first().name to testMoney)
        )

        var prize = bettingBoard.adjustment(testPlayers.first().name, Result.WIN)
        assertAll(
            { assertThat(prize).isEqualTo(testMoney * 2) },
            { assertThat(bettingBoard.getRemain()).isEqualTo(-1 * testMoney) }
        )
    }

    @Test
    fun `무승부한 플레이어에게 원금을 지급하고 보유 금액을 차감한다`() {
        val testMoney = 3000
        val bettingBoard = BettingBoard(
            mapOf(testPlayers.first().name to testMoney)
        )

        val prize = bettingBoard.adjustment(testPlayers.first().name, Result.DRAW)
        assertAll(
            { assertThat(prize).isEqualTo(testMoney) },
            { assertThat(bettingBoard.getRemain()).isEqualTo(0) }
        )
    }

    @Test
    fun `패배한 플레이어에게 아무것도 지급하지 않는다`() {
        val testMoney = 3000
        val bettingBoard = BettingBoard(
            mapOf(testPlayers.first().name to testMoney)
        )

        val prize = bettingBoard.adjustment(testPlayers.first().name, Result.LOSE)
        assertAll(
            { assertThat(prize).isEqualTo(0) },
            { assertThat(bettingBoard.getRemain()).isEqualTo(testMoney) }
        )
    }

    @Test
    fun `존재하지 않는 플레이어의 정산을 요청할 경우 예외를 던진다`() {
        val unknownUser = "unknownUser1"
        val bettingBoard = BettingBoard(
            mapOf(testPlayers.first().name to 1000)
        )

        assertThatThrownBy { bettingBoard.adjustment(unknownUser, Result.WIN) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("존재하지 않는 플레이어입니다.")
    }

    @Test
    fun `남은 금액을 반환한다`() {
        val testMoney = 2000
        val bettingBoard = BettingBoard(
            testPlayers.associate { it.name to testMoney }
        )

        assertThat(bettingBoard.getRemain()).isEqualTo(testMoney * testPlayers.size)
    }
}
