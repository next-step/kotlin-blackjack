package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({
    "각 카드 숫자 중 10, J, Q, K는 서로 같은 숫자 10으로 계산한다" {
        listOf(CardNumber.TEN, CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING).forEach { cardNumber ->
            cardNumber.baseValue shouldBe 10
        }
    }

    "ACE는 biggerValue가 11이다" {
        val sut = CardNumber.ACE

        sut.baseValue shouldBe 1
        sut.biggerValue() shouldBe 11
    }

    "나머지 CardNumber는 biggerValue가 baseValue와 같다" {
        CardNumber.entries
            .asSequence()
            .filter { it != CardNumber.ACE }
            .forEach { cardNumber ->
                cardNumber.biggerValue() shouldBe cardNumber.baseValue
            }
    }
})
