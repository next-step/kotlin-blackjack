package blackjack.domain.participantion

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class PriceTest : StringSpec({
    "금액은 음수일 경우 예외 발생하다." {
        val message = shouldThrow<IllegalArgumentException> {
            Price(-1)
        }

        message shouldHaveMessage "금액은 0 이상 입니다."
    }

    "금액은 음수로 더할경우 예외 발생하다." {
        val price = Price(100)

        val message = shouldThrow<IllegalArgumentException> {
            price.increase(Price(-1))
        }

        message shouldHaveMessage "금액은 0 이상 입니다."
    }

    "금액은 음수로 뺄경우 예외 발생하다." {
        val price = Price(100)

        val message = shouldThrow<IllegalArgumentException> {
            price.decrease(Price(-1))
        }

        message shouldHaveMessage "금액은 0 이상 입니다."
    }
})
