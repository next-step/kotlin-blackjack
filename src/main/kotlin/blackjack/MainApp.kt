package blackjack

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    ResultView.showResultOfSetUp(game.players)

    while (!game.isOver()) {
        val currentPlayer = game.currentPlayer()
        val reply = InputView.readReplyToDrawing(currentPlayer)

        val playerAfterDrawing = game.giveChanceToDraw(reply)
        val isEmptyDeck = ResultView.showStateOfCards(playerAfterDrawing, reply)

        if (isEmptyDeck) break
    }

    ResultView.showGameResult(game.result())
}
