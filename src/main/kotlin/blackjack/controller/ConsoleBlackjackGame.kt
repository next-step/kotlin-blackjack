package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.view.CommandView
import blackjack.view.inputPlayerBettingMoney
import blackjack.view.inputPlayerNames
import blackjack.view.printCurrentDrawResult
import blackjack.view.printDealerGetDraw
import blackjack.view.printFirstDrawResult
import blackjack.view.printGameResult
import blackjack.view.requestAdditionalDraw

class ConsoleBlackjackGame {
    fun run() {
        val players: List<Participant> = inputPlayerNames().map { Player.of(it, inputPlayerBettingMoney(it)) }
        val blackJackGame = BlackjackGame(players = Participants(players))

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
            CommandView.NO -> blackjackGame.passToNextTurn()
            CommandView.YES -> printCurrentDrawResult(blackjackGame.currentPlayerDraw())
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
}
