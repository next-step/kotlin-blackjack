package blackjack.domain.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.view.InputView
import blackjack.domain.view.OutputView

class BlackjackController {
    private val game = BlackjackGame()

    fun run() {
        val names = InputView.readNames()
        game.initialize(names)
        OutputView.printInitialCards(game.getPlayersState())
        playAllTurns()
        showResults()
    }

    private fun playAllTurns() {
        game.getPlayerNames().forEach { name ->
            playTurn(name)
        }
    }

    private fun playTurn(name: String) {
        while (game.isRunning(name) && InputView.readMoreCard(name)) {
            game.drawCard(name)
            OutputView.printPlayerCards(name, game.getPlayerHands(name))
        }
    }

    private fun showResults() {
        OutputView.printResults(game.getPlayersState())
    }
}
