package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
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
        When("getSum() 메서드를 통해") {
            Then("자신의 합을 반환한다.") {
                forAll(
                    row(Hand(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.ACE))), 12),
                    row(
                        Hand(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN))),
                        12
                    ),
                    row(Hand(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN))), 20),
                    row(Hand(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN))), 21),
                    row(
                        Hand(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.ACE))),
                        21
                    ),
                    row(
                        Hand(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.TWO))),
                        22
                    ),
                ) { hand, expected ->
                    hand.getSum() shouldBe expected
                }
            }
        }
    }

    Given("패가 주어지면") {
        When("추가로 Hit 할 수 있는지 없는지를") {
            Then("판단하여 반환한다.") {
                forAll(
                    row(Hand(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN))), true),
                    row(Hand(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN))), false),
                    row(
                        Hand(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.TWO))),
                        false
                    ),
                ) { hand, expected ->
                    hand.canHit() shouldBe expected
                }
            }
        }
    }
})
