package blackjack.entity

import blackjack.entity.Dealer.Companion.MAX_SCORE_TO_DRAW
import blackjack.entity.Dealer.Companion.MIN_SCORE_TO_STAND
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DealerTest : BehaviorSpec({
    Given("딜러의 초기 카드 합계가 특정 조건일 때") {
        When("딜러의 합계가 $MAX_SCORE_TO_DRAW 이하일 경우") {
            val dealer = Dealer()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SIX))

            Then("추가로 카드 한장을 받을 수 있다") {
                dealer.shouldDrawCard() shouldBe true
            }
        }

        When("딜러의 합계가 $MIN_SCORE_TO_STAND 이상일 경우") {
            val dealer = Dealer()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SEVEN))

            Then("추가로 카드를 받을 수 없다") {
                dealer.shouldDrawCard() shouldBe false
            }
        }
    }
    Given("딜러가 자신의 행동을 결정할 때") {
        When("점수가 16 이하인 경우") {
            val dealer = Dealer()
            val deck = Deck()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SIX))

            Then("딜러는 카드를 추가로 받고, 상태가 DRAW가 된다") {
                val action = dealer.playTurn(deck)

                action shouldBe PlayerAction.DRAW
            }
        }

        When("점수가 17 이상인 경우") {
            val dealer = Dealer()
            val deck = Deck()
            dealer.receiveCard(Card(Suit.HEARTS, Rank.TEN))
            dealer.receiveCard(Card(Suit.SPADES, Rank.SEVEN))

            Then("딜러는 카드를 추가로 받지 않고, 상태가 STAND가 된다") {
                val action = dealer.playTurn(deck)

                action shouldBe PlayerAction.STAND
            }
        }
    }

    Given("딜러가 플레이어 결과를 기반으로 자신의 결과를 계산할 때") {
        val dealer =
            Dealer().apply {
                receiveCard(Card(Suit.HEARTS, Rank.TEN))
                receiveCard(Card(Suit.CLUBS, Rank.SEVEN))
            }

        When("플레이어가 모두 딜러에게 승리한 경우") {
            val players =
                Players(
                    listOf(
                        Player("pobi", BettingAmount(1000)).apply {
                            receiveCard(Card(Suit.SPADES, Rank.KING))
                            receiveCard(Card(Suit.HEARTS, Rank.QUEEN))
                        },
                        Player("jason", BettingAmount(2000)).apply {
                            receiveCard(Card(Suit.CLUBS, Rank.TEN))
                            receiveCard(Card(Suit.DIAMONDS, Rank.NINE))
                        },
                    ),
                )

            Then("딜러는 손실한다") {
                val dealerResult = dealer.calculateResult(players)

                dealerResult.earning shouldBe -3000
            }
        }

        When("딜러가 모든 플레이어를 이긴 경우") {
            val players =
                Players(
                    listOf(
                        Player("pobi", BettingAmount(1000)).apply {
                            receiveCard(Card(Suit.HEARTS, Rank.TWO))
                            receiveCard(Card(Suit.SPADES, Rank.THREE))
                        },
                        Player("jason", BettingAmount(2000)).apply {
                            receiveCard(Card(Suit.CLUBS, Rank.FOUR))
                            receiveCard(Card(Suit.DIAMONDS, Rank.FIVE))
                        },
                    ),
                )

            Then("딜러는 모든 플레이어에게 베팅금을 얻는다") {
                val dealerResult = dealer.calculateResult(players)

                dealerResult.earning shouldBe 3000
            }
        }

        When("딜러와 플레이어가 모두 무승부일 경우") {
            val players =
                Players(
                    listOf(
                        Player("pobi", BettingAmount(1000)).apply {
                            receiveCard(Card(Suit.HEARTS, Rank.TEN))
                            receiveCard(Card(Suit.SPADES, Rank.SEVEN))
                        },
                        Player("jason", BettingAmount(2000)).apply {
                            receiveCard(Card(Suit.DIAMONDS, Rank.TEN))
                            receiveCard(Card(Suit.CLUBS, Rank.SEVEN))
                        },
                    ),
                )

            Then("딜러는 모든 플레이어와 무승부로 처리된다") {
                val dealerResult = dealer.calculateResult(players)

                dealerResult.earning shouldBe 0
            }
        }
    }
})
