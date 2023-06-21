package blackjack.controller

import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private val inputView = InputView()
    private val blackjackView = BlackjackView()

    fun run() {
        val players = inputView.inputPlayers()

        val initialDraw = 2
        repeat(initialDraw) {
            players.players.forEach { it.drawCard() }
        }

        blackjackView.printInitialTurn(players.players.map { it.name }, initialDraw)
        players.players.forEach {
            blackjackView.printPlayerCard(it)
        }
    }
}

fun main() {
    BlackjackController().run()
}
