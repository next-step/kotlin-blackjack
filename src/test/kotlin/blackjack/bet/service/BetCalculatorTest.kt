package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.common.service.BlackJackDetermine
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BetCalculatorTest : StringSpec({
    "딜러가 승리할 경우 딜러가 배팅 금액을 가진다" {
        val dealer = BetDealer()
        val player = BetPlayer("tester")
        player.chargeWallet(50_000)

        BetCalculator.updateScores(BlackJackDetermine.Winner.DEALER, player, dealer)
        dealer.wallet().balance() shouldBe 50_000
        dealer.wallet().income() shouldBe 50_000
        player.wallet().balance() shouldBe 0
        player.wallet().income() shouldBe -50_000
    }

    "플레이어가 승리할 경우 플레이어에게 배팅 금액을 가진다" {
        val dealer = BetDealer()
        val player = BetPlayer("tester")
        player.chargeWallet(50_000)

        BetCalculator.updateScores(BlackJackDetermine.Winner.PLAYER, player, dealer)
        dealer.wallet().balance() shouldBe -50_000
        dealer.wallet().income() shouldBe -50_000
        player.wallet().balance() shouldBe 100_000
        player.wallet().income() shouldBe 50_000
    }
})
