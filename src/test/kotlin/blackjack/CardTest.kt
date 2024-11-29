package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "각 카드의 모양은 스페이드, 클로버, 하트, 다이아몬드가 존재한다" {
        Suit.entries.forEach { suit ->
            val sut = Card(CardNumber.ACE, suit)
            sut.suit shouldBe suit
        }
    }
})
