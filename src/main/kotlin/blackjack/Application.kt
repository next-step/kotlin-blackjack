package blackjack

import blackjack.domain.BlackjackGame
import blackjack.view.CommandView.NO
import blackjack.view.CommandView.YES
import blackjack.view.requestAdditionalDraw
import blackjack.view.inputPlayerNames
import blackjack.view.printCurrentDrawResult
import blackjack.view.printFirstDrawResult
import blackjack.view.printGameResults

fun main() {
    val blackJackGame = BlackjackGame.from(inputPlayerNames())

    printFirstDrawResult(blackJackGame.firstDraw())

    runBlackjackGamePlayerTurn(blackJackGame)
    printGameResults(blackJackGame.gameResult())
}

private tailrec fun runBlackjackGamePlayerTurn(blackjackGame: BlackjackGame) {
    if (blackjackGame.isPlayerTurnEnd()) {
        return
    }
    executeByCommand(blackjackGame)
    runBlackjackGamePlayerTurn(blackjackGame)
}

private fun executeByCommand(blackjackGame: BlackjackGame) {
    when (requestAdditionalDraw(blackjackGame.currentTurnPlayerName())) {
        NO -> blackjackGame.passToNextTurn()
        YES -> printCurrentDrawResult(blackjackGame.currentPlayerDraw())
    }
}
