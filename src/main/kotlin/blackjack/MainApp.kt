package blackjack

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    ResultView.showResultOfSetUp(game.setUp())

    while (!game.isOver()) {
        val currentPlayer = game.currentPlayer()
        val reply = InputView.readReplyToDrawing(currentPlayer)

        val playerAfterReply = game.giveChanceToDraw(currentPlayer, reply)
        ResultView.showStateOfCards(playerAfterReply, reply)
    }
    ResultView.showGameResult(game.result())
}
