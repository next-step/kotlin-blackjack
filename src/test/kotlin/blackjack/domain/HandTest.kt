package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

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

    Given("패가 생성된 후") {
        val hand = Hand()
        When("히트를 하면") {
            val newHand = hand.hit(FixedDeck())
            Then("카드 1장을 새로 가진다.") {
                newHand.cards shouldContainExactly listOf(
                    Card(CardSuit.HEART, CardNumber.TWO)
                )
            }
        }
    }

    Given("패는") {
        val hand = Hand(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN)))
        When("getSum() 메서드를 통해") {
            val sum = hand.getSum()
            Then("자신의 합을 반환한다.") {
                sum shouldBe 21
            }
        }
    }
})
