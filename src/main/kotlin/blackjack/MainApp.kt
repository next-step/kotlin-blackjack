package blackjack

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)
    ResultView.showResultOfSetUp(game.dealer, game.players)

    while (!game.isOver()) {
        val currentPlayer = game.currentPlayer()
        val reply = InputView.readReplyToDrawing(currentPlayer)

        val playerAfterDrawing = game.giveChanceToDrawing(reply)
        val isPlayerWithLastCard = ResultView.showStateOfCards(playerAfterDrawing, reply)

        if (isPlayerWithLastCard) break
    }
    ResultView.showPlayOfDealer(game.playOfDealer())

    ResultView.showScoreResult(game.dealer, game.players)
    ResultView.showMatchResult(game.getResult())
}
