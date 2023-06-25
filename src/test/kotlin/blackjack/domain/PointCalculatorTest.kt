package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PointCalculatorTest : BehaviorSpec({

    Given("카드가 들어 있는 덱이 있다") {
        val deck = Deck(
            listOf(
                Card(Suit.SPADE, CardNumber.ACE), // 이때는 11
                Card(Suit.SPADE, CardNumber.NINE),
                Card(Suit.HEART, CardNumber.ACE), // 이때는 1
            ),
        )
        When("해당덱의 점수를 구하면") {
            Then("합이 반환된다") {
                PointCalculator.calculatePoint(deck) shouldBe 21
            }
        }
    }
})
