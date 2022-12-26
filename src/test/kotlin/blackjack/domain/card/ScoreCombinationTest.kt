package blackjack.domain.card

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * @see ScoreCombination
 */
class ScoreCombinationTest : FunSpec({

    context("객체 생성") {
        test("최초 객체 생성 시 점수는 0점이다.") {
            val testScoreCombination = ScoreCombination()

            testScoreCombination.calculateScore() shouldBe 0
        }
    }

    context("fun update(): 새로운 카드를 받으면 점수 조합을 최신화 한다.") {
        val anyCardShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyCardShape)

        test("새로운 카드를 받으면 점수 조합을 최신화 한다.") {
            val testScoreCombination = ScoreCombination()

            shouldNotThrowAny {
                testScoreCombination.update(cardOfAce)
            }
        }
    }

    context("fun calculateScore(): 21 이하면서 가장 큰 수 또는 21 초과이면서 가장 작은 수를 반환한다.") {
        val anyCardShape = mockk<CardShape>()
        val cardOfAce = Card(CardNumber.ACE, anyCardShape)
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)

        test("21 이하의 숫자 조합이 있을 때는 21 이하 중 21에 가장 가까운 수를 반환한다.") {
            val testScoreCombination = ScoreCombination()

            testScoreCombination.update(cardOfAce)
            testScoreCombination.update(cardOfAce)

            testScoreCombination.calculateScore() shouldBe 12
        }

        test("21 이하의 숫자 조합이 없을 때는 21 초과 중 21에 가장 가까운 수를 반환한다.") {
            val testScoreCombination = ScoreCombination()

            testScoreCombination.update(cardOfAce)
            testScoreCombination.update(cardOfTen)
            testScoreCombination.update(cardOfTen)
            testScoreCombination.update(cardOfTen)

            testScoreCombination.calculateScore() shouldBe 31
        }
    }

    context("fun isFull(): 숫자 조합 중 21이 있거나 최소값이 21을 넘었는지 여부") {
        val anyCardShape = mockk<CardShape>()
        val cardOfTen = Card(CardNumber.TEN, anyCardShape)

        test("숫자 조합 중 최소값이 21 미만이라면 false를 반환한다.") {
            val testScoreCombination = ScoreCombination()

            testScoreCombination.update(cardOfTen)

            testScoreCombination.isFull() shouldBe false
        }

        test("숫자 조합 중 최소값이 21 이상이라면 true를 반환한다.") {
            val testScoreCombination = ScoreCombination()

            testScoreCombination.update(cardOfTen)
            testScoreCombination.update(cardOfTen)
            testScoreCombination.update(cardOfTen)

            testScoreCombination.isFull() shouldBe true
        }
    }
})
