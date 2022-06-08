package game.blackjack

import game.blackjack.domain.Player
import game.blackjack.domain.Table
import game.blackjack.view.InputView
import game.blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val players = inputView.readNames().map { Player(it) }
    val table = Table(
        players,
        { inputView.readPlayerAction(it) },
        { resultView.printPlayerCard(it) }
    )

    table.start()
    resultView.printAllPlayerCard(players)
    table.distribute()
    resultView.printResult(players)
}
