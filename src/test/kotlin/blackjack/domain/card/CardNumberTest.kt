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
                is CardNumber.Two -> it.number shouldBe 2
                is CardNumber.Three -> it.number shouldBe 3
                is CardNumber.Four -> it.number shouldBe 4
                is CardNumber.Five -> it.number shouldBe 5
                is CardNumber.Six -> it.number shouldBe 6
                is CardNumber.Seven -> it.number shouldBe 7
                is CardNumber.Eight -> it.number shouldBe 8
                is CardNumber.Nine -> it.number shouldBe 9
                is CardNumber.Ten -> it.number shouldBe 10
                is CardNumber.Jack -> it.number shouldBe 10
                is CardNumber.Queen -> it.number shouldBe 10
                is CardNumber.King -> it.number shouldBe 10
                is CardNumber.Ace -> it.number shouldBe 11
            }
        }
    }

    "Ace는 기본값 11이 아닌 1의 값을 가질 수 있다." {
        CardNumber.Ace.getSwitchedNumber() shouldBe CardNumber.SWITCHED_ACE_NUMBER
    }
})
