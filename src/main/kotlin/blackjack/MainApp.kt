package blackjack

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val playerNames = InputView.readPlayerNames()
    val game = Game(playerNames)

    while (!game.canTurnOver()) {
        game.getStake(InputView.readStake(game.currentPlayer()))
    }

    game.resetTurn()

    ResultView.showResultOfSetUp(game.dealer, game.players)

    while (!game.isOver) {
        val player = game.currentPlayer()
        val replyOfPlayer: String =
            InputView.readReplyToDrawing(player)

        game.giveChanceToDraw(replyOfPlayer)
        ResultView.showCardsDetail(player, replyOfPlayer)
    }
    ResultView.showPlayOfDealer(game.playOfDealer())

    game.checkWin()
    ResultView.showScoreResult(game.dealer, game.players)
    ResultView.showMatchResult(game.dealer, game.players)
}
