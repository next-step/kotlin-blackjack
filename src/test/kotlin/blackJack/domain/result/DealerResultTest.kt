package blackJack.domain.result

import blackJack.domain.enums.BlackjackResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerResultTest {

    private lateinit var winPlayersResult: PlayersResult
    private lateinit var losePlayersResult: PlayersResult
    private lateinit var drawPlayersResult: PlayersResult

    @BeforeEach
    fun setUp() {
        winPlayersResult = PlayersResult(
            listOf(
                PlayerResult("win1", 0, BlackjackResult.WIN),
                PlayerResult("win2", 0, BlackjackResult.WIN),
            ),
        )

        losePlayersResult = PlayersResult(
            listOf(
                PlayerResult("lose1", 0, BlackjackResult.LOSE),
                PlayerResult("lose2", 0, BlackjackResult.LOSE),
            ),
        )

        drawPlayersResult = PlayersResult(
            listOf(
                PlayerResult("draw1", 0, BlackjackResult.DRAW),
                PlayerResult("draw2", 0, BlackjackResult.DRAW),
            ),
        )
    }

    @Test
    fun `2 명의 플레이어가 승을 했으면 dealer 는 lose 가 2개 반환되야 한다`() {
        val dealerResult = DealerResult.calculateResult(winPlayersResult)
        assertEquals(2, dealerResult.lose)
        assertEquals(0, dealerResult.draw)
        assertEquals(0, dealerResult.win)
    }

    @Test
    fun `2 명의 플레이어가 패를 했으면 dealer 는 win 가 2개 반환되야 한다`() {
        val dealerResult = DealerResult.calculateResult(losePlayersResult)
        assertEquals(0, dealerResult.lose)
        assertEquals(0, dealerResult.draw)
        assertEquals(2, dealerResult.win)
    }

    @Test
    fun `2 명의 플레이어가 무승부를 했으면 dealer 는 draw 가 2개 반환되야 한다`() {
        val dealerResult = DealerResult.calculateResult(drawPlayersResult)
        assertEquals(0, dealerResult.lose)
        assertEquals(2, dealerResult.draw)
        assertEquals(0, dealerResult.win)
    }
}
