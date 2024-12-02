package blackjack.controller

import blackjack.view.InputView

class BlackjackController(private val inputView: InputView) {
    fun start() {
        val playerNames = inputView.getPlayerNames()
        println(playerNames)
    }
}
