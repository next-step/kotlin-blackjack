package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    val deck = FixedDeck()

    Given("패가 주어졌을 때") {
        val hand = Hand()
        val initCard = deck.init()
        When("초기화를 하면") {
            hand.init(initCard[0], initCard[1])
            Then("카드 2장을 새로 가진다.") {
                hand.cards shouldContainExactly listOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.EIGHT))
            }
        }
    }

    Given("패가 생성된 후") {
        val hand = Hand()
        val card = deck.hit()
        When("receive를 하면") {
            hand.receive(card)
            Then("카드 1장을 새로 가진다.") {
                hand.cards shouldContainExactly listOf(
                    Card(CardSuit.HEART, CardNumber.TWO)
                )
            }
        }
    }

    Given("패는") {
        When("getSum() 메서드를 통해") {
            Then("자신의 합을 반환한다.") {
                forAll(
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.ACE))), 12),
                    row(
                        Hand(
                            mutableListOf(
                                Card(CardSuit.SPADE, CardNumber.ACE),
                                Card(CardSuit.CLUB, CardNumber.ACE),
                                Card(CardSuit.CLUB, CardNumber.TEN)
                            )
                        ),
                        12
                    ),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN))), 20),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN))), 21),
                    row(
                        Hand(
                            mutableListOf(
                                Card(CardSuit.SPADE, CardNumber.TEN),
                                Card(CardSuit.CLUB, CardNumber.TEN),
                                Card(CardSuit.HEART, CardNumber.ACE)
                            )
                        ),
                        21
                    ),
                    row(
                        Hand(
                            mutableListOf(
                                Card(CardSuit.SPADE, CardNumber.TEN),
                                Card(CardSuit.CLUB, CardNumber.TEN),
                                Card(CardSuit.HEART, CardNumber.TWO)
                            )
                        ),
                        22
                    ),
                ) { hand, expected ->
                    hand.getSum() shouldBe expected
                }
            }
        }
    }
})
