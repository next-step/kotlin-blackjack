package blackjack.domain.result

import blackjack.domain.BetAmount
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Players
import blackjack.test.TestObjectGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameResultGeneratorTest : StringSpec({
    val handBlackjack = TestObjectGenerator.handOfBlackjack()
    val hand20 = TestObjectGenerator.handOf20()
    val hand19 = TestObjectGenerator.handOf19()
    val betAmount10_000 = BetAmount(10_000)

    "플레이어가 (10_000원 배팅, 블랙잭) (10_000원 베팅, 블랙잭)이면 딜러의 수익은 -30000원이다." {
        val player1 = TestObjectGenerator.player(hand = handBlackjack, betAmount = betAmount10_000)
        val player2 = TestObjectGenerator.player(hand = handBlackjack, betAmount = betAmount10_000)

        val gameResult = GameResultGenerator(dealer = Dealer(hand20), players = Players(listOf(player1, player2)))

        gameResult.getDealerEarningAmount() shouldBe -30_000
    }

    "플레이어가 (10_000원 배팅, 패) (10_000원 베팅, 블랙잭)이면 딜러의 수익은 -5_000원이다." {
        val player1 = TestObjectGenerator.player(hand = hand19, betAmount = betAmount10_000)
        val player2 = TestObjectGenerator.player(hand = handBlackjack, betAmount = betAmount10_000)

        val gameResult = GameResultGenerator(dealer = Dealer(hand20), players = Players(listOf(player1, player2)))

        gameResult.getDealerEarningAmount() shouldBe -5_000
    }
    "플레이어가 (10_000원 배팅, 블랙잭) (10_000원 베팅, 블랙잭)이고 딜러가 블랙잭이면 딜러의 수익은 -2_000원이다." {
        val player1 = TestObjectGenerator.player(hand = handBlackjack, betAmount = betAmount10_000)
        val player2 = TestObjectGenerator.player(hand = handBlackjack, betAmount = betAmount10_000)

        val gameResult =
            GameResultGenerator(dealer = Dealer(handBlackjack), players = Players(listOf(player1, player2)))

        gameResult.getDealerEarningAmount() shouldBe -20_000
    }

    "플레이어가 (10_000원 배팅, 무승부) (10_000원 베팅, 승)이면 딜러의 수익은 -1_000원이다." {
        val player1 = TestObjectGenerator.player(hand = hand19, betAmount = betAmount10_000)
        val player2 = TestObjectGenerator.player(hand = hand20, betAmount = betAmount10_000)

        val gameResult =
            GameResultGenerator(dealer = Dealer(hand19), players = Players(listOf(player1, player2)))

        gameResult.getDealerEarningAmount() shouldBe -10_000
    }
})
