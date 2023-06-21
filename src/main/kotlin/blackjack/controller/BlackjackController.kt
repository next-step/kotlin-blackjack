package blackjack.controller

import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private val inputView = InputView()
    private val blackjackView = BlackjackView()

    fun run() {
        val players = inputView.inputPlayers()

        blackjackView.printInitialTurn(players.players.map { it.name })
    }
}

fun main() {
    BlackjackController().run()
}
