package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardScoreCalculatorTest : FreeSpec({
    "카드 점수 계산기는 규칙을 적용해 사용자 카드들의 점수를 반환한다." {
        forAll(
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.SPADE, CardNumber.JACK),
                ), 21
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.SPADE, CardNumber.NINE),
                    Card(Suit.SPADE, CardNumber.TEN),
                    Card(Suit.SPADE, CardNumber.THREE),
                ), 23
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.CLOVER, CardNumber.ACE),
                    Card(Suit.DIAMOND, CardNumber.NINE),
                ), 21
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.CLOVER, CardNumber.ACE),
                    Card(Suit.DIAMOND, CardNumber.EIGHT),
                ), 20
            ),
        ) { cards, expected ->

            val result = CardScoreCalculator.calculate(cards)

            result shouldBe expected
        }
    }
})
