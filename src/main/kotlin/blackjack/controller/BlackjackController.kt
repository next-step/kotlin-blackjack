package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val game = BlackJackGame()

        val names = InputView.inputPlayersName()
        game.setInitDealer()
        game.setInitPlayers(names)

        ResultView.printInitialStatus(game)
        game.play()
        game.calculateResult()
        ResultView.printStatus(game)
        ResultView.printResults(game)
    }
}

fun main() {
    BlackjackController().run()
}
