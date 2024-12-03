package blackjack.domain.betting

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BettingTest : StringSpec({
    "베팅 금액은 음수 값이면 예외를 발생시킨다," {
        shouldThrow<IllegalArgumentException> { Betting(-1) }
    }

    "applyRate()는 베팅 금액에 배당률을 곱한 값을 반환한다," {
        val betting = Betting(10000)
        betting.applyRate(5.0) shouldBe 50000.0
    }
})
