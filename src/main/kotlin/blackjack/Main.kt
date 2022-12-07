package blackjack

import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val players: List<Player> = inputView.inputNames()

    val resultView = ResultView()
    resultView.show(players)
}
