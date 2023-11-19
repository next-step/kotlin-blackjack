package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.strategy.RandomStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val game = BlackjackGame(InputView.inputPlayers(), RandomStrategy(), OutputView())
        game.start()
    }
}

fun main() {
    BlackjackController().start()
}
