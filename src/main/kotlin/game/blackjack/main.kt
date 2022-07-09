package game.blackjack

import game.blackjack.domain.Dealer
import game.blackjack.domain.Player
import game.blackjack.domain.Players
import game.blackjack.domain.Table
import game.blackjack.view.InputView
import game.blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val players = Players(
        Dealer(),
        inputView.readNames().map { Player(it, inputView.readMoney(it)) }
    )
    val table = Table(
        players,
        { inputView.readPlayerAction(it) },
        { resultView.printPlayerCard(it) },
    )

    resultView.printAllPlayerCard(table.init())
    table.distribute()
    resultView.printScore(players)
    resultView.printResult(players)
}
