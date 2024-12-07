package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : FunSpec({
    context("ScoreCalculator 점수 계산 테스트") {
        test("ACE가 없는 경우 점수를 올바르게 계산한다") {
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.TWO, CardType.SPADE),
                        Card(CardNumber.THREE, CardType.HEART),
                        Card(CardNumber.FIVE, CardType.DIAMOND),
                    ),
                )

            val score = ScoreCalculator.calculate(cards)

            score shouldBe 10 // 2 + 3 + 5
        }

        test("ACE가 1개인 경우 최적의 점수를 계산한다") {
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.ACE, CardType.SPADE),
                        Card(CardNumber.KING, CardType.HEART),
                    ),
                )

            val score = ScoreCalculator.calculate(cards)

            score shouldBe 21 // 11 + 10
        }

        test("ACE가 2개인 경우 최적의 점수를 계산한다") {
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.ACE, CardType.SPADE),
                        Card(CardNumber.ACE, CardType.DIAMOND),
                        Card(CardNumber.NINE, CardType.HEART),
                    ),
                )

            val score = ScoreCalculator.calculate(cards)

            score shouldBe 21 // 11 + 1 + 9
        }

        test("21을 넘지 않는 최적의 점수를 계산한다") {
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.TWO, CardType.DIAMOND),
                        Card(CardNumber.FIVE, CardType.HEART),
                        Card(CardNumber.SIX, CardType.CLOVER),
                        Card(CardNumber.ACE, CardType.DIAMOND),
                    ),
                )

            val score = ScoreCalculator.calculate(cards)

            score shouldBe 14 // 2 + 5 + 6 + 1 = 14
        }

        test("ACE가 없는 카드들만으로 점수를 계산한다") {
            val cards =
                Cards.of(
                    listOf(
                        Card(CardNumber.KING, CardType.SPADE),
                        Card(CardNumber.QUEEN, CardType.HEART),
                        Card(CardNumber.JACK, CardType.DIAMOND),
                    ),
                )

            val score = ScoreCalculator.calculate(cards)

            score shouldBe 30 // 10 + 10 + 10
        }
    }
})
