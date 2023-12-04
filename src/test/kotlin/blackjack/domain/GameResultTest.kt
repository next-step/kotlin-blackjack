package blackjack.domain

import blackjack.test.TestDeckGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({

    val deck = TestDeckGenerator.generate(Symbol.SPADE withRank Rank.ACE)

    given("플레이어가 1000원 배팅했을 때") {
        val player = Player(Nickname("플레이어"), Amount(1000.0))
        val dealer = Dealer(deck)

        `when`("플레이어 13점 / 딜러 21점이면") {
            player.apply {
                receiveCard(Card(Symbol.SPADE, Rank.KING))
                receiveCard(Card(Symbol.SPADE, Rank.THREE))
            }
            dealer.apply {
                receiveCard(Card(Symbol.SPADE, Rank.ACE))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
            }

            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 수익이 -1000원이다.") {
                gameResult.playerResults[player]?.getValue() shouldBe -1000.0
            }

            then("딜러는 수익이 1000원이다.") {
                gameResult.dealerResults[0].getValue() shouldBe 1000.0
            }
        }
    }

    given("플레이어가 2000원 배팅했을 때") {
        val player = Player(Nickname("플레이어"), Amount(2000.0))
        val dealer = Dealer(deck)

        `when`("플레이어가 13점 / 딜러 22점이 주어졌을 때") {
            player.apply {
                receiveCard(Card(Symbol.SPADE, Rank.KING))
                receiveCard(Card(Symbol.SPADE, Rank.THREE))
            }
            dealer.apply {
                receiveCard(Card(Symbol.SPADE, Rank.TWO))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
            }
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 수익이 2000 원이다.") {
                gameResult.playerResults[player]?.getValue() shouldBe 2000.0
            }

            then("딜러는 수익이 -2000 원이다.") {
                gameResult.dealerResults[0].getValue() shouldBe -2000.0
            }
        }
    }

    given("플레이어가 3000원 배팅했을 때") {
        val player = Player(Nickname("플레이어"), Amount(3000.0))
        val dealer = Dealer(deck)

        `when`("플레이어 22점 / 딜러 22점이 주어졌을 때") {
            player.apply {
                receiveCard(Card(Symbol.SPADE, Rank.TWO))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
            }
            dealer.apply {
                receiveCard(Card(Symbol.SPADE, Rank.TWO))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
            }
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 수익이 3000 원이다.") {
                gameResult.playerResults[player]?.getValue() shouldBe 3000.0
            }

            then("딜러는 수익이 -3000 원이다.") {
                gameResult.dealerResults[0].getValue() shouldBe -3000.0
            }
        }
    }

    given("플레이어가 4000원 배팅했을 때") {
        val player = Player(Nickname("플레이어"), Amount(4000.0))
        val dealer = Dealer(deck)

        `when`("플레이어 21점 / 딜러 2점이 주어졌을 때") {
            player.apply {
                receiveCard(Card(Symbol.SPADE, Rank.ACE))
                receiveCard(Card(Symbol.SPADE, Rank.TEN))
            }
            dealer.apply {
                receiveCard(Card(Symbol.SPADE, Rank.TWO))
            }
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 수익이 6000 원이다.") {
                gameResult.playerResults[player]?.getValue() shouldBe 6000.0
            }

            then("딜러는 수익이 -6000 원이다.") {
                gameResult.dealerResults[0].getValue() shouldBe -6000.0
            }
        }
    }
})
