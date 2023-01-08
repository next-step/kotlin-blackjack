package blackjack

import blackjack.controller.Casino
import blackjack.domain.Gamer
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val gamers: List<Gamer> = inputView.inputNames()

    val casino = Casino(gamers)
    casino.drawTwoCards()

    val resultView = ResultView()
    resultView.showPlayers(casino)

    casino.relay(inputView.ask(), resultView.showPlayer())

    resultView.showResult(casino)
    resultView.showReport(casino)
}
