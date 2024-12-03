package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CardRankTest : BehaviorSpec({
    Given("Ace, Face, Number 타입을 가진다") {
        listOf(
            "A" to CardRank.ACE,
            "K" to CardRank.KING,
            "9" to CardRank.NINE,
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
                    CardRank.from("A").calculateScore(value) shouldBe expected
                }
            }
        }
    }

    Given("Face는 10으로 계산할 수 있다") {
        val expected = 10
        When("Face일 때") {
            Then("결과는 $expected 이어야 한다") {
                CardRank.from("K").calculateScore(0) shouldBe expected
            }
        }
    }

    Given("Number는 2~10까지의 숫자로 계산할 수 있다") {
        listOf(
            CardRank.TWO to 2,
            CardRank.FIVE to 5,
            CardRank.NINE to 9,
        ).forAll { (cardRank, expected) ->
            When("입력값이 $cardRank 일 때") {
                Then("결과는 $expected 이어야 한다") {
                    cardRank.calculateScore(0) shouldBe expected
                }
            }
        }
    }

    Given("범주에 속하지 않는 문자를 넣으면") {
        val input = "X"
        When("입력값이 $input 일 때") {
            Then("IllegalArgumentException이 발생한다") {
                runCatching { CardRank.from(input) }.isFailure shouldBe true
            }
        }
    }
})
