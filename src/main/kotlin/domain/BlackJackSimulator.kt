package domain

import domain.BlackJackGame.startGame
import view.InputView
import view.ResultView

fun main() {
    val players = InputView.getUserName()
    startGame(players)
    ResultView.displayResult(players)
}
