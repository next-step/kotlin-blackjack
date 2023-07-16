package blackjack

import blackjack.domain.Blackjack
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val blackjack = Blackjack(playerNames)
    val players = blackjack.players

    ResultView.printInit(players)

    blackjack.play()

    ResultView.printResult(players)
}
