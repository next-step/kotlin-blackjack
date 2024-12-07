package blackjack.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    Given("플레이어와 딜러의 점수를 비교하여 결과를 계산할 때") {
        forAll(
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.CLUBS, Rank.SEVEN))
                    receiveCard(Card(Suit.CLUBS, Rank.FIVE))
                },
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                1000,
                "딜러가 버스트인 경우",
            ),
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.CLUBS, Rank.SEVEN))
                },
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.CLUBS, Rank.TEN)),
                1000,
                "플레이어가 딜러보다 21에 가까운 경우",
            ),
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.SPADES, Rank.KING))
                },
                listOf(Card(Suit.DIAMONDS, Rank.NINE), Card(Suit.SPADES, Rank.SEVEN)),
                -1000,
                "딜러가 플레이어보다 21에 가까운 경우",
            ),
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.SPADES, Rank.SEVEN))
                },
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)),
                0,
                "딜러와 플레이어가 동일한 거리인 경우",
            ),
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.SPADES, Rank.SEVEN))
                },
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN), Card(Suit.CLUBS, Rank.FIVE)),
                -1000,
                "플레이어가 버스트인 경우",
            ),
            row(
                Dealer().apply {
                    receiveCard(Card(Suit.HEARTS, Rank.TEN))
                    receiveCard(Card(Suit.SPADES, Rank.SEVEN))
                    receiveCard(Card(Suit.SPADES, Rank.FIVE))
                },
                listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN), Card(Suit.CLUBS, Rank.FIVE)),
                -1000,
                "플레이어와 딜러 모두 버스트인 경우",
            ),
        ) { dealer, playerCards, expectedEarning, description ->

            When(description) {
                val player = Player("Pobi", BettingAmount(1000))
                playerCards.forEach { player.receiveCard(it) }

                val result = player.calculateResult(dealer)

                Then("결과는 플레이어 수익이 ${expectedEarning}원이 되어야 한다") {
                    result.earning shouldBe expectedEarning
                }
            }
        }
    }

    Given("게임 턴을 진행시") {
        When("플레이어가 Hit을 선택한 경우") {
            val player = Player("Pobi", BettingAmount(1000))
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
            val player = Player("Pobi", BettingAmount(1000))
            val deck = Deck()
            val action = player.playTurn(deck, false)

            Then("플레이어의 행동은 Stand이어야 한다") {
                action shouldBe PlayerAction.STAND
            }
        }

        When("플레이어가 버스트 상태인 경우") {
            val player = Player("Pobi", BettingAmount(1000))
            val deck = Deck()
            player.receiveCard(Card(Suit.SPADES, Rank.KING))
            player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))
            player.receiveCard(Card(Suit.CLUBS, Rank.TWO))
            val action = player.playTurn(deck, true)

            Then("플레이어의 행동은 Burst이어야 한다") {
                action shouldBe PlayerAction.BURST
            }
        }
        When("플레이어가 카드를 추가로 받은 결과로 버스트 상태가 된 경우") {
            val player = Player("pobi", BettingAmount(1000))
            val deck = Deck()
            player.receiveCard(Card(Suit.SPADES, Rank.KING))
            player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))

            Then("PlayerAction.BURST를 반환해야 한다") {
                val action = player.playTurn(deck, wantsToHit = true)
                action shouldBe PlayerAction.BURST
            }
        }
        When("플레이어가 블랙잭 상태인 경우") {
            val player = Player("Pobi", BettingAmount(1000))
            val deck = Deck()
            player.receiveCard(Card(Suit.SPADES, Rank.ACE))
            player.receiveCard(Card(Suit.DIAMONDS, Rank.KING))
            val action = player.playTurn(deck, true)

            Then("플레이어의 행동은 Blackjack이어야 한다") {
                action shouldBe PlayerAction.BLACKJACK
            }
        }
    }
})
