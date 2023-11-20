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
                        Dealer(FixedDeck(), Hand(cards)).hit(card)
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
                        Dealer(FixedDeck(), Hand(cards)).hit(card)
                    }
                }
            }
        }
    }

    Given("처음 카드 2장을 받았을 때") {
        When("합이 21이라면") {
            Then("상태를 BLACKJACK으로 바꾼다.") {
                forAll(
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.ACE)), State.BLACKJACK),
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.SIX)), State.HIT),
                ) { cards, expected ->
                    val dealer = Dealer(FixedDeck())
                    dealer.init(cards)
                    dealer.state shouldBe expected
                }
            }
        }
    }

    Given("히트할 때마다") {
        When("딜러는") {
            Then("자신이 가진 상태를 바꾼다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.ACE), State.HIT), // 총합 16점
                    row(Card(CardSuit.SPADE, CardNumber.TWO), State.STAND), // 총합 17점
                    row(Card(CardSuit.SPADE, CardNumber.THREE), State.STAND), // 총합 18점
                    row(Card(CardSuit.SPADE, CardNumber.FOUR), State.STAND), // 총합 19점
                    row(Card(CardSuit.SPADE, CardNumber.FIVE), State.STAND), // 총합 20점
                    row(Card(CardSuit.SPADE, CardNumber.SIX), State.STAND), // 총합 21점
                    row(Card(CardSuit.SPADE, CardNumber.SEVEN), State.BUST), // 총합 22점
                ) { card, expected ->
                    val dealer = Dealer(FixedDeck())
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.TEN))
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.FIVE))
                    dealer.hit(card)
                    dealer.state shouldBe expected
                }
            }
        }
    }

    Given("딜러는 자신이 가진 패로") {
        When("추가로 Hit 할 수 있는지 없는지를") {
            Then("판단하여 반환한다.") {
                forAll(
                    row(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.FIVE)), true),
                    row(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.SIX)), true),
                    row(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.SEVEN)), false),
                    row(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.EIGHT)), false),
                ) { cards, expected ->
                    val dealer = Dealer(FixedDeck())
                    dealer.hit(cards[0])
                    dealer.hit(cards[1])
                    dealer.canHit() shouldBe expected
                }
            }
        }
    }

    Given("딜러는 덱을 갖고") {
        val deck = FixedDeck()
        When("플레이어가 카드를 필요로 할 때마다") {
            val dealer = Dealer(deck)
            Then("카드를 한장씩 나누어준다.") {
                dealer.draw() shouldBe Card(CardSuit.HEART, CardNumber.TWO)
            }
        }
    }

    Given("처음 게임이 시작하면") {
        val deck = FixedDeck()
        When("딜러는") {
            val dealer = Dealer(deck)
            val initCards = dealer.drawInitCards()
            Then("카드를 2장씩 나누어줄 수 있다.") {
                initCards[0] shouldBe Card(CardSuit.HEART, CardNumber.TWO)
                initCards[1] shouldBe Card(CardSuit.SPADE, CardNumber.EIGHT)
            }
        }
    }
})
