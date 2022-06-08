package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val players = inputView.inputPlayers()
    resultView.players(players)
}
