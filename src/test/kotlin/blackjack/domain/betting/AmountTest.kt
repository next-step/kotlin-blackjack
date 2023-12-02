package blackjack.domain.betting

import blackjack.domain.batting.Amount
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class AmountTest : StringSpec({
    "0보다 큰 베팅 금액이 생성된다" {
        val amount = 3_000

        val result = Amount.betAmount(amount)

        result.value shouldBe amount
    }

    "0이하의 금액은 베팅 금액이 될 수 없다" {
        val amount = 0

        shouldThrowExactly<IllegalArgumentException> {
            Amount.betAmount(amount)
        }
    }
})
