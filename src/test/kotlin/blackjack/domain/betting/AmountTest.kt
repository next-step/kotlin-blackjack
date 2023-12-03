package blackjack.domain.betting

import blackjack.domain.batting.Amount
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException
import java.math.BigDecimal

class AmountTest : StringSpec({
    "0보다 큰 베팅 금액이 생성된다" {
        val amount = 3_000

        val result = Amount.betAmount(amount)

        result.value shouldBe amount.toBigDecimal()
    }

    "0이하의 금액은 베팅 금액이 될 수 없다" {
        val amount = 0

        shouldThrowExactly<IllegalArgumentException> {
            Amount.betAmount(amount)
        }
    }

    "금액 끼리는 더할수 있다" {
        val result = Amount(BigDecimal(3_000)) + Amount(BigDecimal(5000))

        result shouldBe Amount(BigDecimal(8_000))
    }

    "금액의 배수(Int)를 구할수 있다" {
        val result = Amount(BigDecimal(3_000)) * 3

        result shouldBe Amount(BigDecimal(9_000))
    }

    "금액의 곱(BigDecimal)을 구할수 있다" {
        val result = Amount(BigDecimal(3_000)) * BigDecimal(3)

        result shouldBe Amount(BigDecimal(9_000))
    }
})
