package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AmountTest : StringSpec({
    "금액 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Amount(100) }
    }

    "금액의 합을 계산할수 있다." {
        val one = Amount(1)
        val two = Amount(2)
        val three = Amount(3)

        one + two shouldBe three
    }

    "금액의 차를 계산할수 있다." {
        val one = Amount(1)
        val two = Amount(2)
        val three = Amount(3)

        three - two shouldBe one
    }

    "금액을 음수로 변경할수 있다." {
        val one = Amount(1)

        -one shouldBe Amount(-1)
    }
})
