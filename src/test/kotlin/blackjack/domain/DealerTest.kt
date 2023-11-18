package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("17점 이상일 때 카드 한장이 주어진다면") {
        val card = Card(CardSuit.SPADE, CardNumber.ACE)
        When("딜러는") {
            Then("에러를 반환한다.") {
                forAll(
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.SEVEN))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.EIGHT))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.NINE))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.TEN))),
                ) { cards ->
                    shouldThrow<IllegalArgumentException> {
                        Dealer(Hand(cards)).hit(card)
                    }
                }
            }
        }
    }

    Given("패가 16점 이하일 때 카드 한장이 주어진다면") {
        val card = Card(CardSuit.SPADE, CardNumber.ACE)
        When("딜러는") {
            Then("정상적으로 패를 받는다.") {
                forAll(
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.THREE))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.FOUR))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.FIVE))),
                    row(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.SIX))),
                ) { cards ->
                    shouldNotThrowAny {
                        Dealer(Hand(cards)).hit(card)
                    }
                }
            }
        }
    }

    Given("딜러는 자신이 가진 패로") {
        When("추가로 Hit 할 수 있는지 없는지를") {
            Then("판단하여 반환한다.") {
                forAll(
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.FIVE))), true),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.SIX))), true),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.SEVEN))), false),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.EIGHT))), false),
                ) { hand, expected ->
                    Dealer(hand).canHit() shouldBe expected
                }
            }
        }
    }
})
