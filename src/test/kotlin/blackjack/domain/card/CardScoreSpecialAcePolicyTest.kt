package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardScoreSpecialAcePolicyTest : FunSpec({
    test("Ace는 11로 계산한다") {
        val card = Card(CardKind.DIAMOND, CardNumber.ACE)
        val actual = CardScoreSpecialAcePolicy.getScore(card)
        actual shouldBe CardScore(11)
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
            CardScoreSpecialAcePolicy.getScore(card) shouldBe CardScore(score)
        }
    }

    context("King, Queen, Jack은 10으로 계산한다.") {
        withData(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING) { cardNumber ->
            val card = Card(CardKind.DIAMOND, cardNumber)
            CardScoreSpecialAcePolicy.getScore(card) shouldBe CardScore(10)
        }
    }

    test("Ace를 11로 계산하여 카드의 합계를 구할 수 있다.") {
        val cardSet = CardSet.of(
            Card(CardKind.DIAMOND, CardNumber.ACE),
            Card(CardKind.DIAMOND, CardNumber.EIGHT)
        )
        cardSet.sum(CardScoreSpecialAcePolicy) shouldBe CardScore(19)
    }

    test("JACK, QUEEN, KING을 10으로 계산하여 카드의 합계를 구할 수 있다.") {
        val cardSet = CardSet.of(
            Card(CardKind.DIAMOND, CardNumber.JACK),
            Card(CardKind.DIAMOND, CardNumber.QUEEN),
            Card(CardKind.DIAMOND, CardNumber.KING)
        )
        cardSet.sum(CardScoreSpecialAcePolicy) shouldBe CardScore(30)
    }
})
