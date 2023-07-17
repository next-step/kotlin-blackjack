package blackjack.domain.game

import blackjack.domain.BLACKJACK_SCORE
import blackjack.domain.BURST_SCORE
import blackjack.domain.SEVENTEEN_SCORE
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GameResultTypeTest {

    @Test
    internal fun `도전자가 먼저 버스트 되면 딜러가 승리한다`() {
        val actual = GameResultType.of(
            challengerScore = BURST_SCORE,
            dealerScore = SEVENTEEN_SCORE,
        )
        actual shouldBe GameResultType.DEALER_WIN
    }

    @Test
    internal fun `도전자가 버스트되지 않았는데 딜러가 버스트되면 점수에 상관없이 도전자가 승리한다`() {
        val actual = GameResultType.of(
            challengerScore = SEVENTEEN_SCORE,
            dealerScore = BURST_SCORE,
        )
        actual shouldBe GameResultType.CHALLENGER_WIN
    }

    @Test
    internal fun `도전자와 딜러 둘다 버스트되지 않았다면 점수가 큰 쪽이 승리한다`() {
        val actual = GameResultType.of(
            challengerScore = BLACKJACK_SCORE,
            dealerScore = SEVENTEEN_SCORE,
        )
        actual shouldBe GameResultType.CHALLENGER_WIN

        val actual2 = GameResultType.of(
            challengerScore = SEVENTEEN_SCORE,
            dealerScore = BLACKJACK_SCORE,
        )
        actual2 shouldBe GameResultType.DEALER_WIN
    }

    @Test
    internal fun `도전자와 딜러 둘다 버스트되지 않았을때 점수가 같으면 비긴다`() {
        val actual = GameResultType.of(
            challengerScore = SEVENTEEN_SCORE,
            dealerScore = SEVENTEEN_SCORE,
        )
        actual shouldBe GameResultType.DRAW
    }
}
