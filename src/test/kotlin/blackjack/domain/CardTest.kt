package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CardTest : BehaviorSpec({
    Given("무늬와 숫자가 주어지면") {
        val suit = CardSuit.DIAMOND
        val num = CardNumber.ACE
        When("카드는") {
            val card = Card(suit, num)
            Then("입력으로 주어진 무늬와 숫자를 가지는 카드가 생성된다.") {
                card shouldBe Card(suit, num)
            }
        }
    }
})
