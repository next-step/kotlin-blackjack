package blackjack.domain.game

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class EarningsRateCaseTest {

    @Test
    internal fun `도전자가 승리했을때 수익률은 1,0 이다`() {
        val sut = EarningsRateCase.of(GameResultType.CHALLENGER_WIN)
        sut.earningsRate shouldBe 1.0
    }

    @Test
    internal fun `도전자가 승리했을때 도전자만 블랙잭이면 수익률은 1,5 이다`() {
        val sut = EarningsRateCase.of(
            gameResultType = GameResultType.CHALLENGER_WIN,
            challengerBlackjacked = true,
            dealerBlackjacked = false,
        )
        sut.earningsRate shouldBe 1.5
    }

    @Test
    internal fun `딜러가 승리했을때 도전자의 수익률은 -1,0 이다`() {
        val sut = EarningsRateCase.of(GameResultType.DEALER_WIN)
        sut.earningsRate shouldBe -1.0
    }

    @Test
    internal fun `비겼을때 도전자의 수익률은 0 이다`() {
        val sut = EarningsRateCase.of(GameResultType.DRAW)
        sut.earningsRate shouldBe 0.0
    }
}
