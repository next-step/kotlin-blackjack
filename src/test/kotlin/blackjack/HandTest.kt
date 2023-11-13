package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly

class HandTest : BehaviorSpec({
    Given("패가 주어졌을 때") {
        val hand = Hand()
        When("초기화를 하면") {
            val newHand = hand.init(FixedDeck())
            Then("카드 2장을 새로 가진다.") {
                newHand.cards shouldContainExactly listOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.HEART, CardNumber.TWO))
            }
        }
    }
})
