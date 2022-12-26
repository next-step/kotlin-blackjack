package blackjack

import blackjack.controller.Casino
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

const val NO = "n"

fun main() {
    val inputView = InputView()
    val players: List<Player> = inputView.inputNames()

    val casino = Casino(players)

    casino.distribute()

    val resultView = ResultView()
    resultView.showPlayers(casino)

    casino.relay(resultView.ask(), resultView.showPlayerCards())

    resultView.showResult(casino)
}
