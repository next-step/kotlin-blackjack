package blackjack

import blackjack.domain.Suit
import blackjack.domain.createDeck
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CardGameTest : BehaviorSpec({
    Given("사용자를 가진다") {
        val userA = "userA"
        val userB = "userB"
        val users = listOf(userA, userB)
        When("사용자 이름을 입력하면") {
            val cardGame =
                CardGame.from(
                    createDeck {
                        cards {
                            "A" to Suit.CLUB
                            "9" to Suit.SPADE
                        }
                    },
                    users,
                )

            Then("사용자를 가진다") {
                cardGame.playersSize shouldBe 2
            }
        }
    }

    Given("두 장의 카드를 가지고 시작한다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    cards {
                        "A" to Suit.CLUB
                        "J" to Suit.SPADE
                        "9" to Suit.SPADE
                        "Q" to Suit.DIAMOND
                    }
                },
                listOf(userA, userB),
            )
        When("사용자 이름을 입력하면") {
            cardGame.startGame(userA)
            val actual = cardGame.userHand(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual.size() shouldBe 2
            }
        }
    }

    Given("지정 유저의 카드를 추가한다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    cards {
                        "J" to Suit.SPADE
                    }
                },
                listOf(userA, userB),
            )
        When("pick 호출하면") {
            cardGame.hand(userA)
            val actual = cardGame.userHand(userA)

            Then("1장의 카드를 가진다") {
                actual.size() shouldBe 1
            }
        }
    }

    Given("유저 카드를 알 수 있다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    cards {
                        "A" to Suit.CLUB
                        "Q" to Suit.DIAMOND
                    }
                },
                listOf(userA, userB),
            )
        When("사용자 이름을 입력하면") {
            cardGame.startGame(userA)
            val actual = cardGame.cardsOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual shouldContainAll listOf("A클로버", "Q다이아몬드")
            }
        }
    }

    Given("유저 점수를 알 수 있다") {
        val userA = "userA"
        val cardGame =
            CardGame.from(
                createDeck {
                    cards {
                        "A" to Suit.CLUB
                        "A" to Suit.DIAMOND
                    }
                },
                listOf(userA),
            )
        cardGame.startGame(userA)

        When("사용자 이름을 입력하면") {
            val actual = cardGame.scoreOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual shouldBe 12
            }
        }
    }
})
