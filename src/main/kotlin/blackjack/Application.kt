package blackjack

import blackjack.domain.BlackjackGame
import blackjack.view.CommandView.NO
import blackjack.view.CommandView.YES
import blackjack.view.inputPlayerNames
import blackjack.view.printCurrentDrawResult
import blackjack.view.printDealerGetDraw
import blackjack.view.printFirstDrawResult
import blackjack.view.printGameResult
import blackjack.view.requestAdditionalDraw

fun main() {
    val blackJackGame = BlackjackGame.from(inputPlayerNames())

    printFirstDrawResult(blackJackGame.firstDraw())

    runBlackjackGamePlayerTurn(blackJackGame)
    runBlackjackGameDealerTurn(blackJackGame)
    printGameResult(blackJackGame.gameResult())
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

private tailrec fun runBlackjackGameDealerTurn(blackjackGame: BlackjackGame) {
    if (blackjackGame.isDealerTurnEnd()) {
        return
    }
    printDealerGetDraw()
    blackjackGame.dealerDraw()
    runBlackjackGameDealerTurn(blackjackGame)
}
