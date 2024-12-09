package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GameResultTest {
    private val result =
        GameResult(
            listOf(
                PlayerResult("black", PlayerOutcome.WIN),
                PlayerResult("jack", PlayerOutcome.LOSE),
                PlayerResult("game", PlayerOutcome.DRAW),
                PlayerResult("player", PlayerOutcome.WIN),
            ),
        )

    @Test
    fun `플레이어의 패바한 수가 딜러가 승리한 수다`() {
        result.dealerWins shouldBe 1
    }

    @Test
    fun `딜러의 무승부 수`() {
        result.dealerDraws shouldBe 1
    }

    @Test
    fun `딜러가 패배한 수가 플레이어 승리한 숫자이다`() {
        result.dealerLosses shouldBe 2
    }
}
