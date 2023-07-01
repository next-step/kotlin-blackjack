package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RefereeTest : FreeSpec({
    "심판은 카드들이 승리, 패배 조건을 만족하는지 확인한다." {
        forAll(
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.SPADE, CardNumber.JACK),
                ), Evaluation.WIN
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.SPADE, CardNumber.NINE),
                    Card(Suit.SPADE, CardNumber.TEN),
                    Card(Suit.SPADE, CardNumber.THREE),
                ), Evaluation.LOSE
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.CLOVER, CardNumber.ACE),
                    Card(Suit.DIAMOND, CardNumber.NINE),
                ), Evaluation.WIN
            ),
            row(
                listOf(
                    Card(Suit.SPADE, CardNumber.ACE),
                    Card(Suit.CLOVER, CardNumber.ACE),
                    Card(Suit.DIAMOND, CardNumber.EIGHT),
                ), Evaluation.CONTINUE
            ),
        ) { cards, expected ->

            val result = Referee.evaluate(cards)

            result shouldBe expected
        }
    }
})
