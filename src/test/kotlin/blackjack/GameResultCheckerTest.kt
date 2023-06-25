package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameResultCheckerTest : StringSpec({
    "딜러의 값이 플레이어보다 높으면 딜러가 승리한다." {
        val dealer = Dealer()
        val gameResultChecker = GameResultChecker(dealer)
        val playerValue = 10
        val dealerValue = 20
        val determineWinner = gameResultChecker.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe GameResultChecker.Winner.DEALER
    }

    "딜러의 값이 21초과인 경우 패배한다." {
        val dealer = Dealer()
        val gameResultChecker = GameResultChecker(dealer)
        val playerValue = 0
        val dealerValue = 22
        val determineWinner = gameResultChecker.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe GameResultChecker.Winner.PLAYER
    }

    "플레이어 값이 딜러의 값보다 높으면 플레이어가 승리한다." {
        val dealer = Dealer()
        val gameResultChecker = GameResultChecker(dealer)
        val playerValue = 21
        val dealerValue = 20
        val determineWinner = gameResultChecker.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe GameResultChecker.Winner.PLAYER
    }

    "딜러와 플레이어가 같은 값을 가지면 무승부이다" {
        val dealer = Dealer()
        val gameResultChecker = GameResultChecker(dealer)
        val playerValue = 21
        val dealerValue = 21
        val determineWinner = gameResultChecker.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe GameResultChecker.Winner.DRAW
    }

    "딜러가 승리할 경우 승패가 잘 기록된다." {
        val dealer = Dealer()
        val player = Player("tester")
        val gameResultChecker = GameResultChecker(dealer)

        gameResultChecker.updateScores(GameResultChecker.Winner.DEALER, player)
        dealer.scoreBoard().win() shouldBe 1
        player.scoreBoard().lose() shouldBe 1
    }

    "플레이어가 승리할 경우 승패가 잘 기록된다." {
        val dealer = Dealer()
        val player = Player("tester")
        val gameResultChecker = GameResultChecker(dealer)

        gameResultChecker.updateScores(GameResultChecker.Winner.PLAYER, player)
        dealer.scoreBoard().lose() shouldBe 1
        player.scoreBoard().win() shouldBe 1
    }

    "무승부의 경우 무승부가 잘 기록된다." {
        val dealer = Dealer()
        val player = Player("tester")
        val gameResultChecker = GameResultChecker(dealer)

        gameResultChecker.updateScores(GameResultChecker.Winner.DRAW, player)
        dealer.scoreBoard().draw() shouldBe 1
        player.scoreBoard().draw() shouldBe 1
    }
})
