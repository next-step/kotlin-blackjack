package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardDequeTest : StringSpec({
    "CardNumber와 CardShape를 이용해 중복 없는 52장의 카드덱을 만든다" {
        val cardDeque = CardDeque.create()
        val expected = 52
        cardDeque.count() shouldBe expected
    }
})
