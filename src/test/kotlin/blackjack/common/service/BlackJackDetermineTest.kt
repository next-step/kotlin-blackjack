package blackjack.common.service

import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.WinType
import blackjack.common.domain.PokerCard
import blackjack.common.domain.PokerSymbol
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackDetermineTest : StringSpec({
    "딜러의 값이 플레이어보다 높으면 딜러가 승리한다." {
        val player = BetPlayer("tester")
        val cards = arrayOf(
            PokerCard(PokerSymbol.CLUBS, 10, "K"),
            PokerCard(PokerSymbol.CLUBS, 2, "2")
        )
        player.hit(*cards)

        val dealerValue = 20
        val determineWinner = BlackJackDetermine.determineWinType(player, dealerValue)
        determineWinner shouldBe WinType.DEALER_WIN
    }

    "딜러의 값이 21초과인 경우 패배한다." {
        val player = BetPlayer("tester")
        val cards = arrayOf(
            PokerCard(PokerSymbol.CLUBS, 10, "K"),
            PokerCard(PokerSymbol.CLUBS, 2, "2")
        )
        player.hit(*cards)

        val dealerValue = 22
        val determineWinner = BlackJackDetermine.determineWinType(player, dealerValue)
        determineWinner shouldBe WinType.DEALER_BUST
    }

    "플레이어의 최초 값이 블랙잭이면 초기 블랙잭으로 승리한다" {
        val player = BetPlayer("tester")
        val cards = arrayOf(
            PokerCard(PokerSymbol.CLUBS, 10, "K"),
            PokerCard(PokerSymbol.CLUBS, 11, "A")
        )
        player.hit(*cards)

        val dealerValue = 20
        val determineWinner = BlackJackDetermine.determineWinType(player, dealerValue)
        determineWinner shouldBe WinType.PLAYER_BLACK_JACK
    }

    "플레이어 값이 딜러의 값보다 높으면 플레이어가 승리한다." {
        val player = BetPlayer("tester")
        val cards = arrayOf(
            PokerCard(PokerSymbol.CLUBS, 10, "K"),
            PokerCard(PokerSymbol.HEARTS, 10, "K")
        )
        player.hit(*cards)

        val dealerValue = 2
        val determineWinner = BlackJackDetermine.determineWinType(player, dealerValue)
        determineWinner shouldBe WinType.PLAYER_WIN
    }

    "딜러와 플레이어가 같은 값을 가지면 무승부이다" {
        val player = BetPlayer("tester")
        val cards = arrayOf(
            PokerCard(PokerSymbol.CLUBS, 10, "K"),
            PokerCard(PokerSymbol.HEARTS, 10, "K")
        )
        player.hit(*cards)

        val dealerValue = 20
        val determineWinner = BlackJackDetermine.determineWinType(player, dealerValue)
        determineWinner shouldBe WinType.DRAW
    }
})
