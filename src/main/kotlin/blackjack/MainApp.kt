package blackjack

import view.InputView
import view.ResultView

fun main() {
    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    val players = game.allPlayers()
    ResultView.showResultOfSetup(players)
}
