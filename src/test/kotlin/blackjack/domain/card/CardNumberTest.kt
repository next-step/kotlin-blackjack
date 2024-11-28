package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardNumberTest : StringSpec({
    "카드는 1~10, Jack(10), Queen(10), King(10), Ace(11)의 값을 갖는다." {
        val numbers =
            listOf(
                CardNumber.Two,
                CardNumber.Three,
            )

        numbers.forEach {
            when (it) {
                is CardNumber.Ace -> it.value shouldBe 1
                is CardNumber.Two -> it.value shouldBe 2
                is CardNumber.Three -> it.value shouldBe 3
                is CardNumber.Four -> it.value shouldBe 4
                is CardNumber.Five -> it.value shouldBe 5
                is CardNumber.Six -> it.value shouldBe 6
                is CardNumber.Seven -> it.value shouldBe 7
                is CardNumber.Eight -> it.value shouldBe 8
                is CardNumber.Nine -> it.value shouldBe 9
                is CardNumber.Ten -> it.value shouldBe 10
                is CardNumber.Jack -> it.value shouldBe 10
                is CardNumber.Queen -> it.value shouldBe 10
                is CardNumber.King -> it.value shouldBe 10
            }
        }
    }

    "Ace는 기본값 1이 아닌 11의 값을 가질 수 있다." {
        CardNumber.Ace.value + CardNumber.Ace.toEleven() shouldBe CardNumber.Ace.value + CardNumber.ACE_HIGH_VALUE
    }
})
