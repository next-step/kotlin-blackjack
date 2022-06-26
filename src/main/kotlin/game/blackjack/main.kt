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
    val dealer = Dealer()
    val table = Table(
        Players(dealer, inputView.readNames().map { Player(it) }),
        { inputView.readPlayerAction(it) },
        { resultView.printPlayerCard(it) },
    )

    resultView.printAllPlayerCard(dealer, table.init())
    resultView.printResult(dealer, table.distribute())
}
