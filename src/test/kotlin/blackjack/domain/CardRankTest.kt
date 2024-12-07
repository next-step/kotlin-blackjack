package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardRankTest : BehaviorSpec({
    Given("ACE는 1 또는 11로 계산할 수 있다") {
        listOf(
            Score(10) to Score(21),
            Score(20) to Score(21),
            Score(21) to Score(22),
        ).forAll { (value, expected) ->
            When("입력값이 $value 일 때") {
                Then("결과는 $expected 이어야 한다") {
                    CardRank.ACE.calculateScore(value) shouldBe expected
                }
            }
        }
    }
})
