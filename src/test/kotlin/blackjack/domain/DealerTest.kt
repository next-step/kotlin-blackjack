package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

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
                        Dealer(FixedDeck(), Hit(Hand(cards))).hit(card)
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
                        Dealer(FixedDeck(), Hit(Hand(cards))).hit(card)
                    }
                }
            }
        }
    }

    Given("처음 카드 2장을 받았을 때 총합이 16점 이하면") {
        When("딜러의 상태는") {
            Then("Hit 상태를 가진다.") {
                forAll(
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.FOUR))), // 총합 14점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.FIVE))), // 총합 15점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.SIX))), // 총합 16점
                ) { cards ->
                    val dealer = Dealer(FixedDeck())
                    dealer.init(cards)

                    val status = dealer.status
                    status.shouldBeInstanceOf<Hit>()
                }
            }
        }
    }

    Given("처음 카드 2장을 받았을 때 총합이 17점 이상 20점 이하면") {
        When("딜러의 상태는") {
            Then("Stay 상태를 가진다.") {
                forAll(
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.SEVEN))), // 총합 17점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT))), // 총합 18점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE))), // 총합 19점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN))), // 총합 20점
                ) { cards ->
                    val dealer = Dealer(FixedDeck())
                    dealer.init(cards)

                    val status = dealer.status
                    status.shouldBeInstanceOf<Stay>()
                }
            }
        }
    }

    Given("처음 카드 2장을 받았을 때 총합이 21점이면") {
        val cards = listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE))
        When("딜러의 상태는") {
            val dealer = Dealer(FixedDeck())
            dealer.init(cards)
            Then("Blackjack 상태를 가진다.") {
                val status = dealer.status
                status.shouldBeInstanceOf<Blackjack>()
            }
        }
    }

    Given("카드를 가져도 총합이 16점이 넘지 않는 카드가 주어지면") {
        When("딜러는") {
            Then("Hit 상태를 가진다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.ACE)), // 총합 14점
                    row(Card(CardSuit.SPADE, CardNumber.TWO)), // 총합 15점
                    row(Card(CardSuit.SPADE, CardNumber.THREE)), // 총합 16점
                ) { card ->
                    val dealer = Dealer(FixedDeck())
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.TEN))
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.THREE))
                    dealer.hit(card)

                    val status = dealer.status
                    status.shouldBeInstanceOf<Hit>()
                }
            }
        }
    }

    Given("카드를 갖게 되면 총합이 17점 이상 21점 이하의 카드가 주어지면") {
        When("딜러는") {
            Then("Stay 상태를 가진다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.FOUR)), // 총합 17점
                    row(Card(CardSuit.SPADE, CardNumber.FIVE)), // 총합 18점
                    row(Card(CardSuit.SPADE, CardNumber.SIX)), // 총합 19점
                    row(Card(CardSuit.SPADE, CardNumber.SEVEN)), // 총합 20점
                    row(Card(CardSuit.SPADE, CardNumber.EIGHT)), // 총합 21점
                ) { card ->
                    val dealer = Dealer(FixedDeck())
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.TEN))
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.THREE))
                    dealer.hit(card)

                    val status = dealer.status
                    status.shouldBeInstanceOf<Stay>()
                }
            }
        }
    }

    Given("카드를 갖게 되면 총합이 21점을 초과하는 카드가 주어지면") {
        When("딜러는") {
            Then("Bust 상태를 가진다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.NINE)), // 총합 22점
                    row(Card(CardSuit.SPADE, CardNumber.TEN)), // 총합 23점
                ) { card ->
                    val dealer = Dealer(FixedDeck())
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.TEN))
                    dealer.hit(Card(CardSuit.CLUB, CardNumber.THREE))
                    dealer.hit(card)

                    val status = dealer.status
                    status.shouldBeInstanceOf<Bust>()
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
