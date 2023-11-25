package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class PlayerTest : BehaviorSpec({
    Given("이름이 주어지면") {
        val name = "pobi"
        When("플레이어는") {
            val player = Player(name)
            Then("주어진 이름을 갖는 플레이어가 생성된다.") {
                player.name shouldBe name
            }
        }
    }

    Given("이름과 패가 주어지면") {
        val name = "pobi"
        val hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.EIGHT)))
        When("플레이어는") {
            val player = Player(name, Hit(hand))
            Then("주어진 이름과 패를 갖는 플레이어가 생성된다.") {
                player.name shouldBe name
                player.hand shouldBe hand
            }
        }
    }

    Given("게임을 처음 시작할 때 카드 2장이 주어지면") {
        val cards = listOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.EIGHT))
        val player = Player("pobi")
        When("플레이어는") {
            player.init(cards)
            Then("주어진 2장의 카드를 패로 갖게 된다.") {
                player.hand.cards[0] shouldBe Card(CardSuit.HEART, CardNumber.TWO)
                player.hand.cards[1] shouldBe Card(CardSuit.SPADE, CardNumber.EIGHT)
            }
        }
    }

    Given("처음 카드 2장을 받았을 때 총합이 20점 이하면") {
        When("플레이어 상태는") {
            Then("Hit 상태를 가진다.") {
                forAll(
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.SEVEN))), // 총합 17점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT))), // 총합 18점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE))), // 총합 19점
                    row(listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN))), // 총합 20점
                ) { cards ->
                    val player = Player("yeongun")
                    player.init(cards)

                    val state = player.state
                    state.shouldBeInstanceOf<Hit>()
                }
            }
        }
    }

    Given("처음 카드 2장을 받았을 때 총합이 21점이면") {
        val cards = listOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.ACE))
        When("플레이어 상태는") {
            val player = Player("yeongun")
            player.init(cards)
            Then("Blackjack 상태를 가진다.") {
                val state = player.state
                state.shouldBeInstanceOf<Blackjack>()
            }
        }
    }

    Given("게임을 처음 시작할 때 2장이 아닌 다른 수의 카드가 주어지면") {
        When("플레이어는") {
            Then("에러를 반환한다.") {
                forAll(
                    row(listOf(Card(CardSuit.HEART, CardNumber.TWO))),
                    row(
                        listOf(
                            Card(CardSuit.HEART, CardNumber.TWO),
                            Card(CardSuit.SPADE, CardNumber.ACE),
                            Card(CardSuit.DIAMOND, CardNumber.TEN)
                        )
                    ),
                ) { cards ->
                    shouldThrow<IllegalArgumentException> {
                        Player("yeongun").init(cards)
                    }
                }
            }
        }
    }

    Given("공백이거나 null인 이름이 주어지면") {
        When("플레이어는") {
            Then("에러를 발생한다.") {
                forAll(
                    row(""),
                    row(" "),
                    row("    "),
                ) { name ->
                    shouldThrow<IllegalArgumentException> {
                        Player(name)
                    }
                }
            }
        }
    }

    Given("카드 1장이 주어지면") {
        val card = Card(CardSuit.SPADE, CardNumber.TEN)
        val player = Player("pobi")
        When("플레이어는") {
            player.hit(card)
            Then("주어진 1장의 카드를 패에 추가한다.") {
                player.hand.cards[0] shouldBe Card(CardSuit.SPADE, CardNumber.TEN)
            }
        }
    }

    Given("카드를 가져도 총합이 21 이하가 되는 카드를 갖게 되면") {
        When("플레이어는") {
            Then("Hit 상태를 가진다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.ACE)), // 총합 19점
                    row(Card(CardSuit.SPADE, CardNumber.TWO)), // 총합 20점
                    row(Card(CardSuit.SPADE, CardNumber.THREE)), // 총합 21점
                ) { card ->
                    val player = Player("yeongun")
                    player.hit(Card(CardSuit.SPADE, CardNumber.TEN))
                    player.hit(Card(CardSuit.CLUB, CardNumber.EIGHT))
                    player.hit(card)

                    val state = player.state
                    state.shouldBeInstanceOf<Hit>()
                }
            }
        }
    }

    Given("카드를 가질 경우 총합이 21을 넘는 카드를 갖게 되면") {
        When("플레이어는") {
            Then("Bust 상태를 가진다.") {
                forAll(
                    row(Card(CardSuit.SPADE, CardNumber.FOUR)), // 총합 22점
                    row(Card(CardSuit.SPADE, CardNumber.FIVE)), // 총합 23점
                ) { card ->
                    val player = Player("yeongun")
                    player.hit(Card(CardSuit.SPADE, CardNumber.TEN))
                    player.hit(Card(CardSuit.CLUB, CardNumber.EIGHT))
                    player.hit(card)

                    val state = player.state
                    state.shouldBeInstanceOf<Bust>()
                }
            }
        }
    }

    Given("플레이어의 패가 버스트 되었는지 판단하고 싶을 때") {
        When("플레이어는") {
            Then("가진 패가 21을 넘으면 Bust, 21을 넘지 않으면 Bust가 아니라고 판단하여 반환한다.") {
                forAll(
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN))), false),
                    row(
                        Hand(
                            mutableListOf(
                                Card(CardSuit.SPADE, CardNumber.TEN),
                                Card(CardSuit.CLUB, CardNumber.TEN),
                                Card(CardSuit.HEART, CardNumber.TWO)
                            )
                        ),
                        true
                    ),
                ) { hand, expected ->
                    Player("yeongun", Hit(hand)).isBust() shouldBe expected
                }
            }
        }
    }

    Given("플레이어는 자신이 가진 패로") {
        When("추가로 Hit 할 수 있는지 없는지를") {
            Then("판단하여 반환한다.") {
                forAll(
                    row(listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN)), false),
                    row(listOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN)), false),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.HEART, CardNumber.TWO)),
                        true
                    )
                ) { cards, expected ->
                    val player = Player("yeongun")
                    cards.forEach {
                        player.hit(it)
                    }
                    player.isFinished() shouldBe expected
                }
            }
        }
    }
})
