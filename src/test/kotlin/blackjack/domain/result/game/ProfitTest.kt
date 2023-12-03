package blackjack.domain.result.game

import blackjack.mock.profit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ProfitTest : StringSpec({
    "profit의 반대 (-profit) 금액을 구할 수 있다" {
        val profit = profit(1_000)

        profit.negative shouldBe profit(-1_000)
    }

    "profit끼리 덧셈이 가능하다" {
        val profit1 = profit(1_000)
        val profit2 = profit(1_000)

        val result = profit1 + profit2

        result shouldBe profit(2_000)
    }
})
