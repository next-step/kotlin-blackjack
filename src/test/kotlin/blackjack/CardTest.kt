package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "각 카드 모양은 스페이드, 클로버, 하트, 다이아몬드가 존재한다" {
        Suit.entries.forEach { suit ->
            val sut = Card(suit, Denomination.ACE)
            sut.suit shouldBe suit
        }
    }

    "각 카드 숫자를 알 수 있다" {
        val suit = Suit.SPADES

        Denomination.entries.forEach { denomination ->
            val sut = Card(suit, denomination)
            sut.number() shouldBe denomination.score
        }
    }
})
