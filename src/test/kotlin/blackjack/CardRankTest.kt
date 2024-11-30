package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardRankTest : BehaviorSpec({
    Given("Ace, Face, Number 타입을 가진다") {
        listOf(
            "A" to CardRank.Ace,
            "K" to CardRank.Face,
            "9" to CardRank.Number(9),
        ).forEach { (input, expected) ->
            When("입력값이 $input 일 때") {
                Then("CardRank는 $expected 이어야 한다") {
                    CardRank.from(input) shouldBe expected
                }
            }
        }
    }

    Given("Ace는 1 또는 11로 계산할 수 있다") {
        listOf(
            10 to 21,
            20 to 21,
        ).forAll { (value, expected) ->
            When("입력값이 $value 일 때") {
                Then("결과는 $expected 이어야 한다") {
                    CardRank.Ace.calculateScore(value) shouldBe expected
                }
            }
        }
    }

    Given("Face는 10으로 계산할 수 있다") {
        val expected = 10
        When("Face일 때") {
            Then("결과는 $expected 이어야 한다") {
                CardRank.Face.calculateScore(0) shouldBe expected
            }
        }
    }

    Given("Number는 2~10까지의 숫자로 계산할 수 있다") {
        listOf(
            2 to 2,
            5 to 5,
            9 to 9,
        ).forAll { (value, expected) ->
            When("입력값이 $value 일 때") {
                Then("결과는 $expected 이어야 한다") {
                    CardRank.Number(value).calculateScore(0) shouldBe expected
                }
            }
        }
    }
})
