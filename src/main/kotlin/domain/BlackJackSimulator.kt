package domain

import domain.BlackJackGame.execute
import view.InputView
import view.ResultView

fun main() {
    val players = InputView.getUserName()
    execute(players)
    ResultView.displayResult(players)
}
