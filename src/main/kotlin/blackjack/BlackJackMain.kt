package blackjack

import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputNames = inputView.playersName()
    val names = inputNames.split(",")
    val players = names.map { Player(it.trim()) { inputView.takeCardAllow(it.trim()) } }

    val cardProvider = CardProvider(players)
    cardProvider.start()
    resultView.startTakeCardPlayers(players)

    cardProvider.play { resultView.takeCardPlayer(it) }
    resultView.playersResult(players)
}
