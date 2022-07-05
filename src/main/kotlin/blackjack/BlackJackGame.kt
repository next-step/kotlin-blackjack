package blackjack

import blackjack.domain.Dealer
import blackjack.domain.GamePlayers
import blackjack.domain.GameResult
import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame {
    private val playGame = PlayGame()
    fun run() {
        val gamePlayers = GamePlayers(
            InputView.getNames().map(::Player),
            Dealer()
        )

        gamePlayers.allPlayers.forEach { player ->
            playGame.start(player)
        }
        OutputView.firstCard(gamePlayers)

        gamePlayers.players.forEach { player ->
            var result: Boolean
            do {
                result = playerHitOrStand(player)
            } while (result)
        }

        dealerHitOrStand(gamePlayers.dealer)

        val gameResults = GameResult.of(gamePlayers)

        OutputView.gameScoreResult(gamePlayers)
        OutputView.gameWinResult(gameResults)
    }

    private fun playerHitOrStand(player: Player): Boolean {
        if (player.canNotHit) {
            println("${player.name}의 카드가 21 이상입니다. 카드를 더 받을 수 없습니다. \n")
            return false
        }
        val hit = InputView.hitOrStand(player.name)
        if (hit) {
            playGame.hit(player)
        }
        OutputView.cardOfPlayer(player)
        if (!hit) return false

        return true
    }

    private fun dealerHitOrStand(dealer: Dealer) {
        if (dealer.canNotHit) {
            return
        }

        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
        playGame.hit(dealer)
    }
}
