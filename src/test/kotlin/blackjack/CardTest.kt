package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "각 카드의 모양은 스페이드, 클로버, 하트, 다이아몬드가 존재한다" {
        Suit.entries.forEach { suit ->
            val sut = Card(CardNumber.Ace, suit)
            sut.suit shouldBe suit
        }
    }

    "각 카드는 자신의 숫자를 반환할 수 있다" {
        val suit = Suit.SPADES

        CardNumberFactory.all().forEach {
            val sut = Card(it, suit)
            sut.number() shouldBe it.baseValue
        }
    }
})
