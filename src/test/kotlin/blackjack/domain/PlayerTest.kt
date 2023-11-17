package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

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
            val player = Player(name, hand)
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

    Given("플레이어는 자신이 가진 패로") {
        When("추가로 Hit 할 수 있는지 없는지를") {
            Then("판단하여 반환한다.") {
                forAll(
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN))), true),
                    row(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.TEN))), false),
                    row(
                        Hand(
                            mutableListOf(
                                Card(CardSuit.SPADE, CardNumber.TEN),
                                Card(CardSuit.CLUB, CardNumber.TEN),
                                Card(CardSuit.HEART, CardNumber.TWO)
                            )
                        ),
                        false
                    ),
                ) { hand, expected ->
                    Player("yeongun", hand).canHit() shouldBe expected
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
})
