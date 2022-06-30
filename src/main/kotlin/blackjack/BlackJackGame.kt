package blackjack

import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackGame {
    fun run() {
        val names = InputView.getNames()
        val players = names.map(::Player)

        val playGame = PlayGame()
        players.forEach { player ->
            playGame.start(player)
        }
        OutputView.firstCard(players)

        players.forEach { player ->
            var result: Boolean
            do {
                result = hitOrStand(playGame, player)
            } while (result)
        }

        OutputView.result(players)
    }

    private fun hitOrStand(playGame: PlayGame, player: Player): Boolean {
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
}
