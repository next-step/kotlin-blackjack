package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({

    "스트링으로 표기 된 숫자 기반으로 카드의 숫자를 반환한다" {
        CardNumber.findNumber("1") shouldBe 1
    }

    "스트링으로 표기 된 특별한 숫자 기반으로 카드의 숫자를 반환한다" {
        CardNumber.findNumber("A") shouldBe 11
    }

    "카드의 숫자는 1부터 11까지이다" {
        shouldThrow<IllegalArgumentException> { CardNumber.findNumber("13") }
    }
})
