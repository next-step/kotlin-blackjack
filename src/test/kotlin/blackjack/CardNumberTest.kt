package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({
    "각 카드 숫자 중 10, J, Q, K는 서로 같은 숫자 10으로 계산한다" {
        listOf(CardNumber.TEN, CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING).forEach { cardNumber ->
            cardNumber.baseValue shouldBe 10
        }
    }
})
