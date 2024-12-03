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
        val dealer = Dealer()

        When("플레이어가 모두 딜러에게 승리한 경우") {
            val playerResults =
                listOf(
                    GameResult(Player("pobi"), wins = 1, loses = 0, draws = 0),
                    GameResult(Player("jason"), wins = 1, loses = 0, draws = 0),
                )

            Then("딜러는 모든 플레이어에게 패배한다") {
                val dealerResult = dealer.calculateResult(playerResults)

                dealerResult.wins shouldBe 0
                dealerResult.loses shouldBe 2
                dealerResult.draws shouldBe 0
            }
        }

        When("딜러가 모든 플레이어를 이긴 경우") {
            val playerResults =
                listOf(
                    GameResult(Player("pobi"), wins = 0, loses = 1, draws = 0),
                    GameResult(Player("jason"), wins = 0, loses = 1, draws = 0),
                )

            Then("딜러는 모든 플레이어에게 승리한다") {
                val dealerResult = dealer.calculateResult(playerResults)

                dealerResult.wins shouldBe 2
                dealerResult.loses shouldBe 0
                dealerResult.draws shouldBe 0
            }
        }

        When("딜러와 플레이어가 모두 무승부일 경우") {
            val playerResults =
                listOf(
                    GameResult(Player("pobi"), wins = 0, loses = 0, draws = 1),
                    GameResult(Player("jason"), wins = 0, loses = 0, draws = 1),
                )

            Then("딜러는 모든 플레이어와 무승부로 처리된다") {
                val dealerResult = dealer.calculateResult(playerResults)

                dealerResult.wins shouldBe 0
                dealerResult.loses shouldBe 0
                dealerResult.draws shouldBe 2
            }
        }
    }
})
