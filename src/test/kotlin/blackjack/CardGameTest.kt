package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.Deck
import blackjack.domain.Suit
import blackjack.domain.createDeck
import blackjack.ui.DealerResult
import blackjack.ui.FinalWinnerResults
import blackjack.ui.UIMatchType
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
                        Card(CardRank.EIGHT, Suit.SPADE),
                        Card(CardRank.EIGHT, Suit.SPADE),
                    ),
                    ArrayDeque(listOf(0, 1, 2, 3, 4, 5)),
                ),
                listOf(userA, userB),
            )
        When("사용자는 카드를 받고 시작한다") {
            cardGame.startGame()
            val actual = cardGame.getPlayerCards(userA)

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
            cardGame.dealCardToPlayer(userA)
            val actual = cardGame.getPlayerCards(userA)

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

            cardGame.dealCardToPlayer(userA)
            cardGame.dealCardToPlayer(userA)
            Then("번호를 그룹화하고 문양들을 가진다.") {
                val actual = cardGame.getFinalRoundResults()
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

    Given("최종 승패") {
        val userA = "userA"
        val userB = "userB"
        listOf(
            CardGame.from(
                Deck(
                    listOf(
                        Card(CardRank.ACE, Suit.DIAMOND),
                        Card(CardRank.NINE, Suit.HEART),
                        Card(CardRank.TWO, Suit.HEART),
                    ),
                    ArrayDeque(listOf(0, 1, 2)),
                ),
                listOf(userA, userB),
            ) to
                FinalWinnerResults(
                    dealerResult = DealerResult(wins = 0, losses = 2),
                    playerResults = mapOf(userA to UIMatchType.WIN, userB to UIMatchType.WIN),
                ),
            CardGame.from(
                Deck(
                    listOf(
                        Card(CardRank.TWO, Suit.DIAMOND),
                        Card(CardRank.ACE, Suit.HEART),
                        Card(CardRank.ACE, Suit.HEART),
                    ),
                    ArrayDeque(listOf(0, 1, 2)),
                ),
                listOf(userA, userB),
            ) to
                FinalWinnerResults(
                    dealerResult = DealerResult(wins = 1, losses = 0, draws = 1),
                    playerResults = mapOf(userA to UIMatchType.LOSS, userB to UIMatchType.DRAW),
                ),
        ).forEach { (cardGame, expectedResult) ->
            When("게임 결과는") {
                cardGame.dealCardToPlayer(userA)
                cardGame.dealCardToPlayer(userB)
                cardGame.dealCardToDealer()
                Then("딜러와 유저 승패를 알 수 있다") {
                    val actual = cardGame.getFinalWinnerResults()
                    actual shouldBe expectedResult
                }
            }
        }

        Given("딜러가 Bust 상태의 카드를 가지면") {
            val userA = "userA"
            val userB = "userB"
            val cardGame =
                CardGame.from(
                    Deck(
                        listOf(
                            Card(CardRank.TWO, Suit.DIAMOND),
                            Card(CardRank.TWO, Suit.HEART),
                            Card(CardRank.JACK, Suit.HEART),
                            Card(CardRank.JACK, Suit.HEART),
                            Card(CardRank.JACK, Suit.HEART),
                        ),
                        ArrayDeque(listOf(0, 1, 2, 3, 4)),
                    ),
                    listOf(userA, userB),
                )
            When("딜러는 패배한다") {
                cardGame.dealCardToPlayer(userA)
                cardGame.dealCardToPlayer(userB)
                cardGame.dealCardToDealer()
                cardGame.dealCardToDealer()
                cardGame.dealCardToDealer()
                Then("딜러는 유저 수만큼 losses를 가진다") {
                    val actual = cardGame.getFinalWinnerResults()
                    actual shouldBe
                        FinalWinnerResults(
                            dealerResult = DealerResult(wins = 0, losses = 2),
                            playerResults = mapOf(userA to UIMatchType.WIN, userB to UIMatchType.WIN),
                        )
                }
            }
        }
    }
})
