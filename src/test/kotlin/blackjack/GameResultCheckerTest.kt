package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameResultCheckerTest : StringSpec({
    "계산 결과로 딜러가 승리한다." {
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

    "계산 결과로 플레이어가 승리한다." {
        val dealer = Dealer()
        val gameResultChecker = GameResultChecker(dealer)
        val playerValue = 21
        val dealerValue = 20
        val determineWinner = gameResultChecker.determineWinner(playerValue, dealerValue)
        determineWinner shouldBe GameResultChecker.Winner.PLAYER
    }

    "같은 값일 경우 무승부가 된다." {
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
        dealer.gameResult() shouldBe "1승 0무 0패"
        player.gameResult() shouldBe "패"
    }

    "플레이어가 승리할 경우 승패가 잘 기록된다." {
        val dealer = Dealer()
        val player = Player("tester")
        val gameResultChecker = GameResultChecker(dealer)

        gameResultChecker.updateScores(GameResultChecker.Winner.PLAYER, player)
        dealer.gameResult() shouldBe "0승 0무 1패"
        player.gameResult() shouldBe "승"
    }

    "무승부의 경우 무승부가 잘 기록된다." {
        val dealer = Dealer()
        val player = Player("tester")
        val gameResultChecker = GameResultChecker(dealer)

        gameResultChecker.updateScores(GameResultChecker.Winner.DRAW, player)
        dealer.gameResult() shouldBe "0승 1무 0패"
        player.gameResult() shouldBe "무"
    }
})
