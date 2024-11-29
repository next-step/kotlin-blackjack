package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({
    "카드는 2~10, Jack(10), Queen(10), King(10), Ace(1)의 값을 갖는다." {
        val numbers = CardNumber.entries
        numbers.forEach { number -> should { number.value in (1..10) } }
    }

    "Ace는 기본값 1이 아닌 11의 값을 가질 수 있다." {
        CardNumber.Ace.value + CardNumber.Ace.toEleven() shouldBe CardNumber.Ace.value + CardNumber.ACE_HIGH_VALUE
    }
})
