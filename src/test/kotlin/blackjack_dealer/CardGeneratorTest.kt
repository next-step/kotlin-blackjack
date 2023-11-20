package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardGeneratorTest : StringSpec({
    "CardGenerator 의 한장 생성 함수가 잘 작동 한다" {
        val deque = CardDeque.create()
        val expected = 51
        CardGenerator(deque).generateSingleCard()
        deque.cardDeque.count() shouldBe expected
    }

    "CardGenerator 의 두장 생성 함수가 잘 작동 한다" {
        val deque = CardDeque.create()
        val expected = 50
        CardGenerator(deque).generateDoubleCards()
        deque.cardDeque.count() shouldBe expected
    }
})
