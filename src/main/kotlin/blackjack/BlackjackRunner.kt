package blackjack

import blackjack.model.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackGame = BlackjackGame(InputView.inputPlayers())
    blackjackGame.withPlayers(OutputView::printInitialState)

    while (!blackjackGame.isGameOver()) {
        val player = blackjackGame.playTurn(InputView::inputConditionToGiveCard)
        OutputView.printPlayer(player)
    }

    blackjackGame.withPlayers(OutputView::printPlayersWithScore)
}
