package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 스페이드, 다이아몬드, 하트, 클로버 무늬로 구분된다." {
        forAll(
            row(Suit.SPADE, Suit.SPADE),
            row(Suit.DIAMOND, Suit.DIAMOND),
            row(Suit.CLUB, Suit.CLUB),
            row(Suit.HEART, Suit.HEART),
        ) { inputSuit, expectedSuit ->

            val card = Card(inputSuit, CardNumber.ACE)

            card.suit shouldBe expectedSuit
        }
    }

    "카드는 A, 2~10, J, Q, K 범위의 숫자를 갖는다." {
        val cardNumberRows = CardNumber
            .values()
            .map { row(it) }
            .toTypedArray()
        forAll(
            *cardNumberRows
        ) { cardNumber ->

            val card = Card(Suit.SPADE, cardNumber)

            card.number shouldBe cardNumber
        }
    }
})
