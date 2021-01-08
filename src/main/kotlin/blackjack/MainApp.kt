package blackjack

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.readSetup()
    val game = Game(playerNames)
    val players = game.allPlayers()
    OutputView.showResultOfSetUp(players)
    while (!game.isOver()) {
        val currentPlayer = game.currentlyPlayer()
        val reply = InputView.replyDraw(currentPlayer)
        val playerAfterDrawing = game.chanceDraw(reply)
        OutputView.showStateOfCards(playerAfterDrawing!!, reply)
    }
    OutputView.showResult(game.result())
}
