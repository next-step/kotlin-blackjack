package blackjack

import blackjack.controller.BlackJackController
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val blackJackApllication = BlackJackApllication()
    blackJackApllication.run()
}

class BlackJackApllication {

    fun run() {
        val blackJackController = BlackJackController(
            inputView = InputView(),
            resultView = ResultView()
        )
        blackJackController.start()
    }

}
