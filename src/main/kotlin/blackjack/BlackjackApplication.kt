package blackjack

import blackjack.controller.BlackjackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackController = BlackjackController(inputView = InputView(), outputView = OutputView())
    blackjackController.start()
}
