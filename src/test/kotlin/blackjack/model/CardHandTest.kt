package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardHandTest : BehaviorSpec({
    Given("번호가 K와 2을 가지고 있는 카드 핸드") {
        val cardHand = CardHand(
            listOf(
                Card(CardValue.K, CardSuit.SPADES),
                Card(CardValue.TWO, CardSuit.SPADES)
            )
        )
        When("점수를 계산") {
            val score = cardHand.totalScore
            Then("12") {
                score shouldBe 12
            }
        }
    }
    Given("번호가 A와 2을 가지고 있는 카드 핸드") {
        val cardHand = CardHand(
            listOf(
                Card(CardValue.A, CardSuit.SPADES),
                Card(CardValue.TWO, CardSuit.SPADES)
            )
        )
        When("점수를 계산") {
            val score = cardHand.totalScore
            Then("13") {
                score shouldBe 13
            }
        }
    }
    Given("번호가 A와 K을 가지고 있는 카드 핸드(소프트 케이스)") {
        val cardHand = CardHand(
            listOf(
                Card(CardValue.A, CardSuit.SPADES),
                Card(CardValue.K, CardSuit.SPADES)
            )
        )
        When("점수를 계산") {
            val score = cardHand.totalScore
            Then("21") {
                score shouldBe 21
            }
        }
    }
    Given("번호가 A와 K와 2을 가지고 있는 카드 핸드(소프트 케이스)") {
        val cardHand = CardHand(
            listOf(
                Card(CardValue.A, CardSuit.SPADES),
                Card(CardValue.K, CardSuit.SPADES),
                Card(CardValue.TWO, CardSuit.SPADES),
            )
        )
        When("점수를 계산") {
            val score = cardHand.totalScore
            Then("13") {
                score shouldBe 13
            }
        }
    }
})
