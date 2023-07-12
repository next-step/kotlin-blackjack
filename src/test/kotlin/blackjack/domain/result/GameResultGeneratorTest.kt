package blackjack.domain.result

import blackjack.domain.hand.Hand
import blackjack.domain.participant.BetAmount
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultGeneratorTest : BehaviorSpec({
    val handBlackjack = TestObjectGenerator.handOfBlackjack()
    val hand20 = TestObjectGenerator.handOf20()
    val hand19 = TestObjectGenerator.handOf19()

    given("모든 플레이어의 베팅금액이 10_000원인 경우") {
        `when`("플레이어가 블랙잭, 블랙잭이면") {
            val players = List(2) {
                playerBet10_000(handBlackjack)
            }
            val dealer = Dealer(hand20)
            val gameResult = GameResultGenerator(dealer = dealer, players = Players(players))

            then("딜러의 수익은 -30_000원 이다.") {
                gameResult.getDealerEarningAmount() shouldBe -30_000

            }
        }

        `when`("플레이어가 패, 블랙잭이면") {
            val player1 = playerBet10_000(hand19)
            val player2 = playerBet10_000(handBlackjack)
            val players = listOf(player1, player2)
            val dealer = Dealer(hand20)
            val gameResult = GameResultGenerator(dealer = dealer, players = Players(players))

            then("딜러의 수익은 -5_000원 이다.") {
                gameResult.getDealerEarningAmount() shouldBe -5_000
            }
        }

        `when`("플레이어가 무승부, 승이면") {
            val player1 = playerBet10_000(hand19)
            val player2 = playerBet10_000(hand20)
            val players = listOf(player1, player2)
            val dealer = Dealer(hand19)
            val gameResult = GameResultGenerator(dealer = dealer, players = Players(players))

            then("딜러의 수익은 -10_000원 이다.") {
                gameResult.getDealerEarningAmount() shouldBe -10_000
            }
        }

        `when`("플레이어가 블랙잭, 블랙잭이고 딜러도 블랙잭이면") {
            val players = List(2) {
                playerBet10_000(handBlackjack)
            }
            val dealer = Dealer(handBlackjack)
            val gameResult = GameResultGenerator(dealer = dealer, players = Players(players))

            then("딜러의 수익은 -20_000원 이다.") {
                gameResult.getDealerEarningAmount() shouldBe -20_000
            }
        }
    }
})

private fun playerBet10_000(hand: Hand): Player {
    return TestObjectGenerator.player(hand = hand, betAmount = BetAmount(10_000))
}
