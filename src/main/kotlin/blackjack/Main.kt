package blackjack

import blackjack.domain.Gamer
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val gamers: List<Gamer> = inputView.inputNames()

    val resultView = ResultView()
    resultView.show(gamers)
}
