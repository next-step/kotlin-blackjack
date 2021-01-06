package blackjack

import view.InputView
import view.ResultView

fun main() {
    val playerNames = InputView.readSetup()
    val game = Game(playerNames)
    val players = game.allPlayers()
    ResultView.showResultOfSetUp(players)
}
