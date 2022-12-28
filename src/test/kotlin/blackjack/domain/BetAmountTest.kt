package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll

internal class BetAmountTest : StringSpec({
    "베팅 금액이 음수라면 에러가 발생한다." {
        val betAmount = -100

        shouldThrow<IllegalArgumentException> {
            BetAmount(betAmount)
        }
    }

    "베팅 금액은 0원이상이어야 한다." {
        val betAmounts = listOf(0, 1000, 100000)

        betAmounts.forAll {
            BetAmount(it)
        }
    }
})
