package blackjack.domain.result

import blackjack.domain.participant.BetAmount
import blackjack.domain.participant.Dealer
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MatchResultTest : BehaviorSpec({
    val hand20 = TestObjectGenerator.handOf20()
    val hand19 = TestObjectGenerator.handOf19()
    val handBlackJack = TestObjectGenerator.handOfBlackjack()
    val handBust = TestObjectGenerator.handOfBust()

    given("딜러가 버스트 상태인 경우") {
        val dealer = Dealer(handBust)

        `when`("플레이어의 점수와 상관없이") {
            val player = TestObjectGenerator.player(hand = hand20, betAmount = BetAmount(10_000))
            val matchResult = MatchResult(player, dealer)

            then("플레이어가 이기고 베팅금액을 얻는다.") {
                matchResult.ofPlayer().result shouldBe Result.WIN
                matchResult.ofPlayer().earningAmount shouldBe 10_000

                matchResult.ofDealer().result shouldBe Result.LOSE
                matchResult.ofDealer().earningAmount shouldBe -10_000
            }
        }
    }

    given("딜러가 버스트 상태가 아닌 경우") {
        val dealer = Dealer(hand20)

        `when`("플레이어가 버스트이면") {
            val player = TestObjectGenerator.player(hand = handBust, betAmount = BetAmount(10_000))
            val matchResult = MatchResult(player, dealer)

            then("딜러가 이기고 베팅 금액을 얻는다.") {
                matchResult.ofDealer().result shouldBe Result.WIN
                matchResult.ofDealer().earningAmount shouldBe 10_000

                matchResult.ofPlayer().result shouldBe Result.LOSE
                matchResult.ofPlayer().earningAmount shouldBe -10_000
            }
        }
    }

    given("딜러와 플레이어가 블랙잭이나 버스트 상태가 아닌 경우") {
        `when`("딜러의 점수가 플레이어의 점수보다 크면") {
            val dealer = Dealer(hand20)
            val player = TestObjectGenerator.player(hand = hand19, betAmount = BetAmount(10_000))
            val matchResult = MatchResult(player, dealer)

            then("딜러가 이기고 베팅금액을 얻는다.") {
                matchResult.ofDealer().result shouldBe Result.WIN
                matchResult.ofDealer().earningAmount shouldBe 10_000

                matchResult.ofPlayer().result shouldBe Result.LOSE
                matchResult.ofPlayer().earningAmount shouldBe -10_000
            }
        }

        `when`("딜러의 점수가 플레이어의 점수보다 작으면") {
            val dealer = Dealer(hand19)
            val player = TestObjectGenerator.player(hand = hand20, betAmount = BetAmount(10_000))
            val matchResult = MatchResult(player, dealer)

            then("플레이어가 이기고 베팅금액을 얻는다.") {
                matchResult.ofPlayer().result shouldBe Result.WIN
                matchResult.ofPlayer().earningAmount shouldBe 10_000

                matchResult.ofDealer().result shouldBe Result.LOSE
                matchResult.ofDealer().earningAmount shouldBe -10_000
            }
        }

        `when`("딜러의 점수가 플레이어의 점수와 같으면") {
            val dealer = Dealer(hand20)
            val player = TestObjectGenerator.player(hand = hand20, betAmount = BetAmount(10_000))
            val matchResult = MatchResult(player, dealer)

            then("무승무이다.") {
                matchResult.ofPlayer().result shouldBe Result.DRAW
                matchResult.ofPlayer().earningAmount shouldBe 0

                matchResult.ofDealer().result shouldBe Result.DRAW
                matchResult.ofDealer().earningAmount shouldBe 0
            }
        }
    }

    given("플레이어의 점수가 블랙잭인 경우") {
        val player = TestObjectGenerator.player(hand = handBlackJack, betAmount = BetAmount(10_000))

        `when`("딜러의 점수가 블랙잭이 아니면") {
            val dealer = Dealer(hand19)
            val matchResult = MatchResult(player, dealer)

            then("플레이어가 이기고 베팅 금액의 1.5배를 얻는다.") {
                matchResult.ofPlayer().result shouldBe Result.WIN
                matchResult.ofPlayer().earningAmount shouldBe 15_000

                matchResult.ofDealer().result shouldBe Result.LOSE
                matchResult.ofDealer().earningAmount shouldBe -15_000
            }
        }

        `when`("딜러의 점수가 블랙잭이면") {
            val dealer = Dealer(handBlackJack)
            val matchResult = MatchResult(player, dealer)

            then("무승부이고 플레이어는 베팅 금액을 얻는다.") {
                matchResult.ofPlayer().result shouldBe Result.DRAW
                matchResult.ofPlayer().earningAmount shouldBe 10_000

                matchResult.ofDealer().result shouldBe Result.DRAW
                matchResult.ofDealer().earningAmount shouldBe -10_000
            }
        }
    }
})
