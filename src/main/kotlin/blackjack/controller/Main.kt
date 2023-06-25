package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.getNames()
    val game = BlackJack(players)
    game.start()
    ResultView.printStart(game)
}
