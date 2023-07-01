package blackjack.domain.game

import blackjack.domain.money.Money
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class MoneyTest : StringSpec({

    "음수로 돈을 만들면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Money(-1)
        }
    }

    "0으로 돈을 만들면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Money(0)
        }
    }
})
