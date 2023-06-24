package blackjack

import blackjack.domain.BlackjackGame
import blackjack.view.CommandView.NO
import blackjack.view.CommandView.YES
import blackjack.view.inputPlayerDraw
import blackjack.view.inputPlayerNames
import blackjack.view.printCurrentDrawResult
import blackjack.view.printFirstDrawResult
import blackjack.view.printGameResults

fun main() {
    val blackJackGame = BlackjackGame.from(inputPlayerNames())

    printFirstDrawResult(blackJackGame.firstDraw())

    runBlackjackGame(blackJackGame)
    printGameResults(blackJackGame.gameResult())
}

private tailrec fun runBlackjackGame(blackjackGame: BlackjackGame) {
    if (blackjackGame.isEndGame()) {
        return
    }
    executeByCommand(blackjackGame)
    runBlackjackGame(blackjackGame)
}

private fun executeByCommand(blackjackGame: BlackjackGame) {
    when (inputPlayerDraw(blackjackGame.currentTurnPlayerName())) {
        NO -> blackjackGame.passToNextTurn()
        YES -> printCurrentDrawResult(blackjackGame.currentPlayerDraw())
    }
}
