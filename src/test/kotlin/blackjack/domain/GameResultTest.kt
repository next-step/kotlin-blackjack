package blackjack.domain

import blackjack.test.TestDeckGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({

    val deck = TestDeckGenerator.generate(Symbol.SPADE withRank Rank.ACE)

    given("플레이어 13점 / 딜러 21점이 주어졌을 때") {
        val player = Player(Nickname("플레이어")).apply {
            receiveCard(Card(Symbol.SPADE, Rank.KING))
            receiveCard(Card(Symbol.SPADE, Rank.THREE))
        }
        val dealer = Dealer(deck).apply {
            receiveCard(Card(Symbol.SPADE, Rank.ACE))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
        }


        `when`("게임 결과를 계산하면") {
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 패배한다.") {
                gameResult.playerResults[player] shouldBe GameOutcome.LOSE
            }

            then("딜러는 1승 0패를 기록한다.") {
                gameResult.getDealerStats() shouldBe (1 to 0)
            }
        }
    }

    given("플레이어 13점 / 딜러 22점이 주어졌을 때") {
        val player = Player(Nickname("플레이어")).apply {
            receiveCard(Card(Symbol.SPADE, Rank.KING))
            receiveCard(Card(Symbol.SPADE, Rank.THREE))
        }
        val dealer = Dealer(deck).apply {
            receiveCard(Card(Symbol.SPADE, Rank.TWO))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
        }

        `when`("게임 결과를 계산하면") {
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 승리한다.") {
                gameResult.playerResults[player] shouldBe GameOutcome.WIN
            }

            then("딜러는 0승 1패를 기록한다.") {
                gameResult.getDealerStats() shouldBe (0 to 1)
            }
        }
    }

    given("플레이어 22점 / 딜러 22점이 주어졌을 때") {
        val player = Player(Nickname("플레이어")).apply {
            receiveCard(Card(Symbol.SPADE, Rank.TWO))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
        }
        val dealer = Dealer(deck).apply {
            receiveCard(Card(Symbol.SPADE, Rank.TWO))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
            receiveCard(Card(Symbol.SPADE, Rank.TEN))
        }

        `when`("게임 결과를 계산하면") {
            val gameResult = GameResult(listOf(player), dealer)

            then("플레이어는 패배한다.") {
                gameResult.playerResults[player] shouldBe GameOutcome.LOSE
            }

            then("딜러는 0승 1패를 기록한다.") {
                gameResult.getDealerStats() shouldBe (1 to 0)
            }
        }
    }
})
