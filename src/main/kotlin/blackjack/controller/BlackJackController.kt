package blackjack.controller

import blackjack.view.InputView

class BlackJackController {
    fun execute() {
        val players = InputView.readName().split(",")
    }
}
