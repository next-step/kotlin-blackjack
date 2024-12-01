package blackjack.entity

import blackjack.entity.Dealer.Companion.MAX_SCORE_TO_DRAW
import blackjack.entity.Dealer.Companion.MIN_SCORE_TO_STAND
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
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
    Given("딜러와 여러 플레이어의 점수를 비교하여 결과를 계산할 때") {
        forAll(
            row(
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.KING), Card(Suit.CLUBS, Rank.TWO)),
                listOf(18, 19, 20),
                0,
                3,
                0,
                "딜러가 Bust인 경우",
            ),
            row(
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SIX)),
                listOf(14, 22, 10),
                2,
                1,
                0,
                "딜러가 21에 가장 가까운 경우",
            ),
            row(
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SIX)),
                listOf(20, 18),
                0,
                2,
                0,
                "플레이어가 21에 더 가까운 경우",
            ),
        ) { dealerCards, playerScores, expectedWins, expectedLoses, expectedDraws, description ->

            When(description) {
                val dealer = Dealer()
                dealerCards.forEach { dealer.receiveCard(it) }

                val score = ComparisonScore.Multiple(playerScores)

                Then("결과는 딜러: ${expectedWins}승 ${expectedLoses}패 ${expectedDraws}무 이어야 한다") {
                    val result = dealer.calculateResult(score)

                    result.wins shouldBe expectedWins
                    result.loses shouldBe expectedLoses
                    result.draws shouldBe expectedDraws
                }
            }
        }
    }
})
