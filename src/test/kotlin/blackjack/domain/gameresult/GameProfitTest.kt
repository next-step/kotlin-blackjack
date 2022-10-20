package blackjack.domain.gameresult

import blackjack.domain.GameProfit.GameProfit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class GameProfitTest : StringSpec({
    "게임 이득 객체의 값을 음의 값으로 바꾼다." {
        // given
        val gameProfit = GameProfit(BigDecimal.valueOf(1_000L))

        // when
        val result = !gameProfit

        // then
        result.value shouldBe BigDecimal(-1_000L)
    }

    "게임 이득 객체를 더한다." {
        // given
        val gameProfit = GameProfit(BigDecimal.valueOf(1_000L))
        val additionalGameProfit = GameProfit(BigDecimal.valueOf(2_000L))

        // when
        val result = gameProfit + additionalGameProfit

        // then
        result.value shouldBe BigDecimal(3_000L)
    }
})
