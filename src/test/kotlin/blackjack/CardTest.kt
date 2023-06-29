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

    "카드는 자신이 가진 번호를 바탕으로 점수로 계산될 수 있다" {
        forAll(
            row(CardNumber.ACE, listOf(1, 11)),
            row(CardNumber.TWO, listOf(2)),
            row(CardNumber.THREE, listOf(3)),
            row(CardNumber.FOUR, listOf(4)),
            row(CardNumber.FIVE, listOf(5)),
            row(CardNumber.SIX, listOf(6)),
            row(CardNumber.SEVEN, listOf(7)),
            row(CardNumber.EIGHT, listOf(8)),
            row(CardNumber.NINE, listOf(9)),
            row(CardNumber.TEN, listOf(10)),
            row(CardNumber.JACK, listOf(10)),
            row(CardNumber.QUEEN, listOf(10)),
            row(CardNumber.KING, listOf(10)),
        ) { cardNumber, expectedScores ->

            val card = Card(Suit.SPADE, cardNumber)

            card.scores shouldBe expectedScores
        }
    }
})
