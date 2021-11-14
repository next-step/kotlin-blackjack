package blackJack

import blackJack.controller.BlackJackController
import blackJack.view.InputView
import blackJack.view.ResultView

fun main() {
    BlackJackController(InputView, ResultView).start()
}
