package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val game = BlackJackGame()

        val names = InputView.inputPlayersName()
        val players = game.makePlayers(names)
        val dealer = game.makeDealer()

        ResultView.printInitialStatus(players, dealer)
        game.play(players, dealer)
        val results = game.getResult(players, dealer)
        ResultView.printStatus(players, dealer)
        ResultView.printResults(results, dealer)
    }
}

fun main() {
    BlackjackController().run()
}
