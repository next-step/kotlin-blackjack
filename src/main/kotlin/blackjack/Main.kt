package blackjack

import blackjack.controller.BlackjackGame
import blackjack.domain.GameTable
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    BlackjackGame(GameTable, InputView, ResultView).start()
}
