package blackjack

import blackjack.controller.BlackJackGame
import blackjack.domain.GameTable
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    BlackJackGame(GameTable, InputView, ResultView).start()
}
