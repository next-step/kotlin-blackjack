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
                    PlayerResult("black", BigDecimal(10_000L)),
                    PlayerResult("jack", BigDecimal(-20_000L)),
                    PlayerResult("game", BigDecimal(0L)),
                ),
            )

        val profit = result.dealerProfit()

        profit shouldBe BigDecimal(10_000L)
    }
}
