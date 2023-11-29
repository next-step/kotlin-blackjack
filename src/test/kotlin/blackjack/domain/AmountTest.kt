package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec

class AmountTest : ExpectSpec({

    expect("금액이 1 이상이면 객체를 반환한다.") {
        Amount(1.0)
    }

    expect("금액은 0 이하이면 예외가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            Amount(0.0)
        }
    }
})
