package blackjack.common.service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackDetermineTest : StringSpec({
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
})
