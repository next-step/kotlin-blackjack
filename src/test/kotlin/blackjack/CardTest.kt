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
    "각 카드의 숫자는 A, 2~10, K, Q, J 가 존재한다" {
        val suit = Suit.SPADES
        CardNumber.entries.forEach { cardNumber ->
            val sut = Card(cardNumber, suit)
            sut.number shouldBe cardNumber
        }
    }
})
