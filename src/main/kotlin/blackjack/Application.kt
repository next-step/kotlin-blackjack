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
    while (blackJackGame.isEndGame().not()) {
        val command = inputPlayerDraw(blackJackGame.currentTurnPlayerName())
        if (command == NO) {
            blackJackGame.passToNextTurn()
        }
        if (command == YES) {
            printCurrentDrawResult(blackJackGame.currentPlayerDraw())
        }
    }
    printGameResults(blackJackGame.gameResult())
}
