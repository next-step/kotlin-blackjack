package blakjack.controller

import blakjack.domain.Game
import blakjack.domain.Player
import blakjack.view.InputView
import blakjack.view.OutputView

class BlackJackController {

    fun start() {
        val players = InputView.readPlayerNames().map { Player(name = it) }
        val game = Game(players = players)

        game.initialDraw()
        OutputView.printInitialPlayerCards(players)

        players.forEach { player ->
            while (InputView.readHitOrStand(player.name)) {
                game.hit(player)
                OutputView.printPlayerCards(player)

                if (player.isBust()) {
                    break
                }
            }
        }
        OutputView.printPlayerCardsWithScore(players)
    }
}
