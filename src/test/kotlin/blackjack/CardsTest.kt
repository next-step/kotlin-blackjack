package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : FunSpec({
    test("카드를 추가할 수 있다.") {
        // given
        val card = Card(CardNumber.ACE, CardSuit.SPADE)
        val sut = Cards()

        // when
        val result: Cards = sut.add(card)

        // then
        result.size() shouldBe 1
    }

    context("Ace 카드는 점수를 특별하게 계산하여") {
        test("손패에 카드들의 점수가 10점 이하인 경우 ACE는 11점으로 계산된다.") {
            // given
            val values = listOf(
                Card(CardNumber.FIVE, CardSuit.SPADE),
                Card(CardNumber.FIVE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.SPADE)
            )
            val sut = Cards(values)

            // when
            val result = sut.totalScore()

            // then
            result shouldBe Score(21)
        }

        test("손패에 카드들의 점수가 에이스를 제외하고 11점 이상인 경우 ACE는 1점으로 계산된다.") {
            // given
            val values = listOf(
                Card(CardNumber.FIVE, CardSuit.SPADE),
                Card(CardNumber.SIX, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.SPADE)
            )
            val sut = Cards(values)

            // when
            val result = sut.totalScore()

            // then
            result shouldBe Score(12)
        }
    }
})
