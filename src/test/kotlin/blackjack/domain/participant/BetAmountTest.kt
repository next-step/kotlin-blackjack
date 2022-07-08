package blackjack.domain.participant

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BetAmountTest : FreeSpec({

    "베팅 금액이 음수인 경우 예외가 발생한다." {
        listOf(
            -1,
            -10,
            -999
        ).forAll {
            val exception = shouldThrowExactly<IllegalArgumentException> { BetAmount(value = it.toDouble()) }
            exception.message shouldBe "베팅 금액은 음수일 수 없습니다."
        }
    }
})
