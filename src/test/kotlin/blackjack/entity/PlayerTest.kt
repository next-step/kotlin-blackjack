package blackjack.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    Given("플레이어와 딜러의 점수를 비교하여 결과를 계산할 때") {
        forAll(
            row(
                ComparisonScore.Dealer(22),
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                1,
                0,
                0,
                "딜러가 Bust인 경우",
            ),
            row(
                ComparisonScore.Dealer(17),
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.CLUBS, Rank.TEN)),
                1,
                0,
                0,
                "플레이어가 딜러보다 21에 가까운 경우",
            ),
            row(
                ComparisonScore.Dealer(20),
                listOf(Card(Suit.DIAMONDS, Rank.NINE), Card(Suit.SPADES, Rank.SEVEN)),
                0,
                1,
                0,
                "딜러가 플레이어보다 21에 가까운 경우",
            ),
            row(
                ComparisonScore.Dealer(17),
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                0,
                0,
                1,
                "딜러와 플레이어가 동일한 거리인 경우",
            ),
        ) { dealerScore, playerCards, expectedWins, expectedLoses, expectedDraws, description ->

            When(description) {
                val player = Player("Pobi")
                playerCards.forEach { player.receiveCard(it) }

                val result = player.calculateResult(dealerScore)

                Then("결과는 플레이어: ${expectedWins}승 ${expectedLoses}패 ${expectedDraws}무 이어야 한다") {
                    result.wins shouldBe expectedWins
                    result.loses shouldBe expectedLoses
                    result.draws shouldBe expectedDraws
                }
            }
        }
    }
})
