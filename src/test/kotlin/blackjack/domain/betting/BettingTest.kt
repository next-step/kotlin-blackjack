package blackjack.domain.betting

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BettingTest : StringSpec({
    "베팅 금액은 음수 값이면 예외를 발생시킨다," {
        shouldThrow<IllegalArgumentException> { Betting(-1) }
    }
})
