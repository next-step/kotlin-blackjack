package blackjack

import blackjack.domain.BlackjackGame
import blackjack.view.inputPlayerDraw
import blackjack.view.inputPlayerNames
import blackjack.view.printCurrentDrawResult
import blackjack.view.printFirstDrawResult

fun main() {
    val blackJackGame = BlackjackGame.from(inputPlayerNames())

    printFirstDrawResult(blackJackGame.firstDraw())
    while (blackJackGame.isEndGame().not()) {
        val command = inputPlayerDraw(blackJackGame.currentTurnPlayerName())
        if (command == "n") {
            blackJackGame.passToNextTurn()
        }
        if (command == "y") {
            printCurrentDrawResult(blackJackGame.currentPlayerDraw())
        }
    }
}
