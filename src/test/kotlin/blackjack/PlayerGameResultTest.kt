package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerWinLoseResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.math.roundToInt

class PlayerGameResultTest : StringSpec({
    "최종 수익을 계산한다." {
        // given
        val result = PlayerGameResult("player1", BetAmount(10000.0), PlayerWinLoseResult.WIN)

        // when
        val earnAmount = result.getEarnAmount()

        // then
        earnAmount shouldBe (10000.0 * 1.0).roundToInt()
    }
})
