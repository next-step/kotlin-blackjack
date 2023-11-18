package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.view.InputView

class BlackjackController {
    fun start() {
        val game = BlackjackGame(InputView.inputPlayers())
    }
}

fun main() {
    BlackjackController().start()
}
