package blackjack

import blackjack.model.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val blackjackGame = BlackjackGame(InputView.inputPlayers())
    OutputView.printInitialState(blackjackGame)

    while (!blackjackGame.isGameOver()) {
        val player = blackjackGame.playTurn(InputView::inputConditionToGiveCard)
        OutputView.printPlayer(player)
    }

    while (!blackjackGame.isDealerGameOver()) {
        OutputView.printPlayer(blackjackGame.playDealer())
    }

    OutputView.printFinalState(blackjackGame)
    OutputView.printWinners(blackjackGame.createResults())
}
