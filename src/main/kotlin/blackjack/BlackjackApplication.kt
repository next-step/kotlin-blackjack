package blackjack

import blackjack.controller.BlackjackController
import blackjack.view.InputView

fun main() {
    val blackjackController = BlackjackController(inputView = InputView())
    blackjackController.start()
}
