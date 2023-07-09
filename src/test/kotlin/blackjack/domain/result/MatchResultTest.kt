package blackjack.domain.result

import blackjack.domain.participant.Dealer
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MatchResultTest : BehaviorSpec({
    val hand20 = TestObjectGenerator.handOf20()
    val hand21 = TestObjectGenerator.handOf21()
    val hand22 = TestObjectGenerator.handOf22()

    given("딜러의 점수가 21보다 클때") {
        val dealer = Dealer(hand22)

        `when`("플레이어의 점수와 상관없이") {
            val player = TestObjectGenerator.player(hand = hand20)
            val matchResult = MatchResult(player, dealer)

            then("플레이어가 이긴다.") {
                matchResult.ofPlayer() shouldBe Result.WIN
                matchResult.ofDealer() shouldBe Result.LOSE
            }
        }
    }

    given("딜러의 점수가 21이하") {
        val dealer = Dealer(hand21)

        `when`("플레이어의 점수가 21보다 크면") {
            val player = TestObjectGenerator.player(hand = hand22)
            val matchResult = MatchResult(player, dealer)
            then("딜러가 이긴다.") {
                matchResult.ofPlayer() shouldBe Result.LOSE
                matchResult.ofDealer() shouldBe Result.WIN
            }
        }
    }

    given("딜러와 플레이어의 점수가 21이하면") {

        `when`("딜러의 점수가 플레이어의 점수보다 크면") {
            val dealer = Dealer(hand21)
            val player = TestObjectGenerator.player(hand = hand20)
            val matchResult = MatchResult(player, dealer)

            then("딜러가 이긴다.") {
                matchResult.ofPlayer() shouldBe Result.LOSE
                matchResult.ofDealer() shouldBe Result.WIN
            }
        }

        `when`("딜러의 점수가 플레이어의 점수보다 작으면") {
            val dealer = Dealer(hand20)
            val player = TestObjectGenerator.player(hand = hand21)
            val matchResult = MatchResult(player, dealer)

            then("플레이어기 이긴다.") {
                matchResult.ofPlayer() shouldBe Result.WIN
                matchResult.ofDealer() shouldBe Result.LOSE
            }
        }

        `when`("딜러의 점수가 플레이어의 점수와 같으면") {
            val dealer = Dealer(hand20)
            val player = TestObjectGenerator.player(hand = hand20)
            val matchResult = MatchResult(player, dealer)

            then("비긴다.") {
                matchResult.ofPlayer() shouldBe Result.DRAW
                matchResult.ofDealer() shouldBe Result.DRAW
            }
        }
    }
})
