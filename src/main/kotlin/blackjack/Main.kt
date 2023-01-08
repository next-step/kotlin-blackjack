package blackjack

import blackjack.controller.Casino
import blackjack.domain.Gamer
import blackjack.ui.InputView
import blackjack.ui.ResultView

const val NO = "n"

fun main() {
    val inputView = InputView()
    val gamers: List<Gamer> = inputView.inputNames()

    val casino = Casino(gamers)
    casino.init()
    casino.distribute()

    val resultView = ResultView()
    resultView.showPlayers(casino)

    casino.relay(resultView.ask(), resultView.showPlayerCards())

    resultView.showResult(casino)
    resultView.showReport(casino)
}
