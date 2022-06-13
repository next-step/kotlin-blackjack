package blackjack.domain.participant.vo

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BetAmountTest : StringSpec({
    "베팅 금액을 생성할수 있다." {
        shouldNotThrow<Throwable> { BetAmount(1_000) }
    }

    "1000 미만의 베팅 금액을 입력할 경우 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> { BetAmount(999) }
    }
})
