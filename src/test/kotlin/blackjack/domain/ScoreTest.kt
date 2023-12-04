package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ScoreTest : BehaviorSpec({

    Given("두 점수가 주어졌을 때") {
        val score1 = Score(10)
        val score2 = Score(20)

        When("더하게 되면") {
            val result = score1 + score2

            Then("두 점수의 합이 반환된다.") {
                result shouldBe Score(30)
            }
        }

        When("큰지 비교했을 때 주어진 값이 더 크면") {
            Then("true가 반환된다.") {
                (score1 < score2).shouldBeTrue()
            }
        }

        When("작은지 비교했을 때 주어진 값이 더 크면") {
            Then("false가 반환된다.") {
                (score1 > score2).shouldBeFalse()
            }
        }
    }
})
