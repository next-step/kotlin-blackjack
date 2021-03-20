package blackjack.controller

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = InputView.readPlayers()

    OutputView.printFirstDraw(players)
}
