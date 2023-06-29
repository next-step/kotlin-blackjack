package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackjackGame(
        InputView,
        InputView,
        InputView,
        OutputView,
        OutputView,
        OutputView,
        OutputView,
        OutputView,
    ).start()
}
