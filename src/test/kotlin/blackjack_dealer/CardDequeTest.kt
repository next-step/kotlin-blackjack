package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDequeTest : StringSpec({
    "CardNumber와 CardShape를 이용해 중복 없는 52장의 카드덱을 만든다" {
        val cardDeque = CardDeque.create()
        val expected = 52
        cardDeque.cardDeque.count() shouldBe expected
    }

    "CardGenerator 의 한장 생성 함수가 잘 작동 한다" {
        val cardDeque = CardDeque.create()
        val expected = 51
        cardDeque.generateSingleCard()
        cardDeque.cardDeque.count() shouldBe expected
    }

    "CardGenerator 의 두장 생성 함수가 잘 작동한다" {
        val cardDeque = CardDeque.create()
        val expected = 50
        cardDeque.generateDoubleCard()
        cardDeque.cardDeque.count() shouldBe expected
    }
})
