package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardGeneratorTest : StringSpec({
    "CardGenerator 의 한장 생성 함수가 잘 작동 한다" {
        val cardDeque = CardDeque.create()
        val expected = 51
        CardGenerator.generateSingleCard(cardDeque)
        cardDeque.cardDeque.count() shouldBe expected
    }
})
