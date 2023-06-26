package blackjack.scorerule.service

import blackjack.common.service.BlackJackDetermine
import blackjack.scorerule.domain.ScoreDealer
import blackjack.scorerule.domain.ScorePlayer
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : StringSpec({
    "딜러의 값이 플레이어보다 높으면 딜러가 승리한다." {
        val playerValue = 10
        val dealerValue = 20
        val determineWinner = BlackJackDetermine.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe BlackJackDetermine.Winner.DEALER
    }

    "딜러의 값이 21초과인 경우 패배한다." {
        val playerValue = 0
        val dealerValue = 22
        val determineWinner = BlackJackDetermine.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe BlackJackDetermine.Winner.DEALER_BUST
    }

    "플레이어 값이 딜러의 값보다 높으면 플레이어가 승리한다." {
        val playerValue = 21
        val dealerValue = 20
        val determineWinner = BlackJackDetermine.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe BlackJackDetermine.Winner.PLAYER
    }

    "딜러와 플레이어가 같은 값을 가지면 무승부이다" {
        val playerValue = 21
        val dealerValue = 21
        val determineWinner = BlackJackDetermine.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe BlackJackDetermine.Winner.DRAW
    }

    "딜러가 승리할 경우 승패가 잘 기록된다." {
        val dealer = ScoreDealer()
        val player = ScorePlayer("tester")

        ScoreCalculator.updateScores(BlackJackDetermine.Winner.DEALER, player, dealer)
        dealer.scoreBoard().win() shouldBe 1
        player.scoreBoard().lose() shouldBe 1
    }

    "플레이어가 승리할 경우 승패가 잘 기록된다." {
        val dealer = ScoreDealer()
        val player = ScorePlayer("tester")

        ScoreCalculator.updateScores(BlackJackDetermine.Winner.PLAYER, player, dealer)
        dealer.scoreBoard().lose() shouldBe 1
        player.scoreBoard().win() shouldBe 1
    }

    "무승부의 경우 무승부가 잘 기록된다." {
        val dealer = ScoreDealer()
        val player = ScorePlayer("tester")

        ScoreCalculator.updateScores(BlackJackDetermine.Winner.DRAW, player, dealer)
        dealer.scoreBoard().draw() shouldBe 1
        player.scoreBoard().draw() shouldBe 1
    }
})
