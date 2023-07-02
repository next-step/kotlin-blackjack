package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : StringSpec({

    "손에 카드들을 가질 때 올바른 점수가 계산되어야 한다" {
        forAll(
            row(
                listOf(
                    Card(CardNumber.ACE, CardSuit.HEART),
                    Card(CardNumber.KING, CardSuit.CLUB)
                ),
                21
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, CardSuit.HEART),
                    Card(CardNumber.ACE, CardSuit.CLUB)
                ),
                12
            ),
            row(
                listOf(
                    Card(CardNumber.ACE, CardSuit.HEART),
                    Card(CardNumber.KING, CardSuit.CLUB),
                    Card(CardNumber.QUEEN, CardSuit.DIAMOND)
                ),
                21
            ),
            row(
                listOf(
                    Card(CardNumber.NINE, CardSuit.HEART),
                    Card(CardNumber.TEN, CardSuit.CLUB),
                    Card(CardNumber.ACE, CardSuit.DIAMOND)
                ),
                20
            )
        ) { cards, expectedScore ->
            val hand = Hand()
            cards.forEach { hand.addCard(it) }
            ScoreCalculator.calculateScore(hand) shouldBe expectedScore
        }
    }
})
