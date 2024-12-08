package blackjack

import blackjack.controller.BlackjackGame
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    BlackjackGame(InputView, ResultView).start()
}
