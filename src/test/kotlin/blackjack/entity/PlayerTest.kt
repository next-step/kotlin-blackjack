package blackjack.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("플레이어와 딜러의 점수를 비교하여 결과를 계산할 때") {
        forAll(
            row(
                22,
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                1,
                0,
                0,
                "딜러가 버스트인 경우",
            ),
            row(
                17,
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.CLUBS, Rank.TEN)),
                1,
                0,
                0,
                "플레이어가 딜러보다 21에 가까운 경우",
            ),
            row(
                20,
                listOf(Card(Suit.DIAMONDS, Rank.NINE), Card(Suit.SPADES, Rank.SEVEN)),
                0,
                1,
                0,
                "딜러가 플레이어보다 21에 가까운 경우",
            ),
            row(
                17,
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                0,
                0,
                1,
                "딜러와 플레이어가 동일한 거리인 경우",
            ),
            row(
                17,
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN), Card(Suit.CLUBS, Rank.FIVE)),
                0,
                1,
                0,
                "플레이어가 버스트인 경우",
            ),
            row(
                22,
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN), Card(Suit.CLUBS, Rank.FIVE)),
                0,
                0,
                1,
                "플레이어와 딜러 모두 버스트인 경우",
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

    Given("게임 턴을 진행시") {
        When("플레이어가 Hit을 선택한 경우") {
            val player = Player("Pobi")
            val deck = Deck()
            val action = player.playTurn(deck, true)

            Then("플레이어는 카드를 받아야 한다") {
                player.hand.cards.size shouldBe 1
            }

            Then("플레이어의 행동은 Hit이어야 한다") {
                action shouldBe PlayerAction.HIT
            }
        }

        When("플레이어가 Stand을 선택한 경우") {
            val player = Player("Pobi")
            val deck = Deck()
            val action = player.playTurn(deck, false)

            Then("플레이어의 행동은 Stand이어야 한다") {
                action shouldBe PlayerAction.STAND
            }
        }

        When("플레이어가 버스트 상태인 경우") {
            val player = Player("Pobi")
            val deck = Deck()
            player.receiveCard(Card(Suit.SPADES, Rank.KING))
            player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))
            player.receiveCard(Card(Suit.CLUBS, Rank.TWO))
            val action = player.playTurn(deck, true)

            Then("플레이어의 행동은 Burst이어야 한다") {
                action shouldBe PlayerAction.BURST
            }
        }
    }
})