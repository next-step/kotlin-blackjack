package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BetTest : StringSpec({

    "주어진 금액을 통해 인스턴스를 생성한다" {
        val bet = Bet(100)

        bet.amount shouldBe 100
    }

    "금액에 음수를 제공하면 에러가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            Bet(-1)
        }
    }
})
