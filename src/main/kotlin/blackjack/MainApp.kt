package blackjack

import view.InputView
import view.ResultView

fun main() {
    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    val players = game.allPlayers()
    ResultView.showResultOfSetup(players)

    while (!game.isOver()){
        val currentPlayer = game.currentlyPlayer()
        val reply = InputView.readReplyToDrawing(currentPlayer)

        val playerAfterDrawing = game.chanceDraw(reply)
        val isEmpty = ResultView.showStateOfCards(playerAfterDrawing!!, reply, game)

        if (isEmpty) break

    }
    ResultView.showGameResult(game.result())
}
