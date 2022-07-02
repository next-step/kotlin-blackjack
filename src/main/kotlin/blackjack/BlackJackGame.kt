package blackjack

import blackjack.domain.Dealer
import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackGame {
    fun run() {
        val names = InputView.getNames()
        val players = names.map(::Player)
        val dealer = Dealer()

        val playGame = PlayGame()
        players.forEach { player ->
            playGame.start(player)
        }
        playGame.start(dealer)
        OutputView.firstCard(players, dealer)

        players.forEach { player ->
            var result: Boolean
            do {
                result = playerHitOrStand(playGame, player)
            } while (result)
        }

        println()
        var dealerHit: Boolean
        do {
            dealerHit = dealerHitOrStand(playGame, dealer)
        } while (dealerHit)

        OutputView.result(players, dealer)
    }

    private fun playerHitOrStand(playGame: PlayGame, player: Player): Boolean {
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

    private fun dealerHitOrStand(playGame: PlayGame, dealer: Dealer): Boolean {
        if (dealer.canNotHit) {
            return false
        }

        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        playGame.hit(dealer)
        return true
    }
}
