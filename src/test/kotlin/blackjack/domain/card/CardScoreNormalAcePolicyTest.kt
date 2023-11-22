package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardScoreNormalAcePolicyTest : FunSpec({
    test("Ace는 1로 계산한다") {
        val card = Card(CardKind.DIAMOND, CardNumber.ACE)
        val actual = CardScoreNormalAcePolicy.getScore(card)
        actual shouldBe CardScore(1)
    }

    context("일반적인 숫자는 해당 값으로 계산한다") {
        withData(
            row(CardNumber.TWO, 2),
            row(CardNumber.THREE, 3),
            row(CardNumber.FOUR, 4),
            row(CardNumber.FIVE, 5),
            row(CardNumber.SIX, 6),
            row(CardNumber.SEVEN, 7),
            row(CardNumber.EIGHT, 8),
            row(CardNumber.NINE, 9),
            row(CardNumber.TEN, 10),
        ) { (cardNumber, score) ->
            val card = Card(CardKind.DIAMOND, cardNumber)
            CardScoreNormalAcePolicy.getScore(card) shouldBe CardScore(score)
        }
    }
})
