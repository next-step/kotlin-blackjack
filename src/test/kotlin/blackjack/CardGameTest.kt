package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Deck
import blackjack.domain.Suit
import blackjack.domain.createDeck
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe

class CardGameTest : BehaviorSpec({
    Given("게임을 시작하면 ") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                Deck(
                    listOf(
                        Card(CardRank.ACE, Suit.CLUB),
                        Card(CardRank.ACE, Suit.SPADE),
                        Card(CardRank.NINE, Suit.SPADE),
                        Card(CardRank.EIGHT, Suit.SPADE),
                    ),
                    ArrayDeque(listOf(0, 1, 2, 3)),
                ),
                listOf(userA, userB),
            )
        When("사용자는 카드를 받고 시작한다") {
            cardGame.playerAllDeal()
            val actual = cardGame.userCardOf(userA)

            Then("사용자는 두 장의 카드를 가진다") {
                actual["A"]?.shouldContainAll(listOf("CLUB", "SPADE"))
            }
        }
    }

    Given("지정 유저의 카드를 추가한다") {
        val userA = "userA"
        val userB = "userB"
        val cardGame =
            CardGame.from(
                createDeck {
                    CardRank.JACK to Suit.SPADE
                },
                listOf(userA, userB),
            )
        When("pick 호출하면") {
            cardGame.deal(userA)
            val actual = cardGame.userCardOf(userA)

            Then("1장의 카드를 가진다") {
                actual.size shouldBe 1
            }
        }
    }

    Given("중복 번호를 가진 카드를") {
        When("유저에게 주면") {
            val userA = "userA"
            val cardGame =
                CardGame.from(
                    Deck(
                        listOf(
                            Card(CardRank.ACE, Suit.DIAMOND),
                            Card(CardRank.ACE, Suit.HEART),
                        ),
                        ArrayDeque(listOf(0, 1)),
                    ),
                    listOf(userA),
                )

            cardGame.deal(userA)
            cardGame.deal(userA)
            Then("번호를 그룹화하고 문양들을 가진다.") {
                val actual = cardGame.result()
                actual shouldContainAll
                    mapOf(
                        userA to
                            mapOf(
                                mapOf("ACE" to listOf("DIAMOND", "HEART")) to 12,
                            ),
                    )
            }
        }
    }
})
