package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class GameResultTest {
    @Test
    fun `딜러의 수익을 계산한다`() {
        val result =
            GameResult(
                listOf(
                    PlayerResult("black", Bet(10_000L), PlayerOutcome.WIN),
                    PlayerResult("jack", Bet(20_000L), PlayerOutcome.LOSE),
                    PlayerResult("game", Bet(30_000L), PlayerOutcome.DRAW),
                    PlayerResult("result", Bet(40_000L), PlayerOutcome.BLACKJACK),
                ),
            )

        val profit = result.dealerProfit()

        profit shouldBe BigDecimal(-50_000L)
    }
}
