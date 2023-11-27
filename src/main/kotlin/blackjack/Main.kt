package blackjack

import blackjack.view.BlackjackController
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    println("abcd")
    BlackjackController(
        inputView = InputView(),
        resultView = ResultView()
    )
}
