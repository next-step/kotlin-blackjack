package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val game = BlackJackGame()

        val names = InputView.inputPlayersName()
        val players = game.makePlayers(names)
        ResultView.printInitialStatus(players)
        game.play(players)
        ResultView.printResult(players)
    }
}

fun main() {
    BlackjackController().run()
}
