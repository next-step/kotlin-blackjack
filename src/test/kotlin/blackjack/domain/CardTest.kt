package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "동일한 숫자와 모양에 대해 동일한 인스턴스를 반환해야 한다." {
        val number = CardNumber.ACE
        val shape = CardShape.HEART
        val card1 = Card.of(number, shape)
        val card2 = Card.of(number, shape)

        card1 shouldBe card2
    }
})
