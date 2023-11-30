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
    fun `10000 원을 건 플레이어가 이겼을 경우 딜러는 -10000 손해를 본다`() {
        val playerResult = PlayerResult("pobi", 10000, BlackjackResult.WIN)
        val playerResults = PlayersResult(listOf(playerResult))
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 졌을 경우 딜러는 10000 이득을 본다`() {
        val playerResult = PlayerResult("pobi", 10000, BlackjackResult.LOSE)
        val playerResults = PlayersResult(listOf(playerResult))
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 졌고, 20000 원을 건 플레이어가 이겼을 경우 경우 딜러는 -10000 손해를 본다`() {
        val playerResult1 = PlayerResult("pobi", 10000, BlackjackResult.LOSE)
        val playerResult2 = PlayerResult("jason", 20000, BlackjackResult.WIN)
        val playerResults = PlayersResult(listOf(playerResult1, playerResult2))
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-10000, dealerResult.dealerProfit)
    }

    @Test
    fun `10000 원을 건 플레이어가 블랙잭으로 이겼을 경우 경우 딜러는 -15000 손해를 본다`() {
        val playerResult = PlayerResult("pobi", 10000, BlackjackResult.BLACKJACK)
        val playerResults = PlayersResult(listOf(playerResult))
        val dealerResult = DealerResult.calculateResult(playerResults)

        assertEquals(-15000, dealerResult.dealerProfit)
    }
}
