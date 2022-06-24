package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGameController {
    fun run() {
        val blackjackGame = BlackjackGame(
            Deck.createOf(),
            createPlayers(),
        )

        blackjackGame.init()
        OutputView.printPlayersInitCards(blackjackGame.players)
        playPlayersTurn(blackjackGame)
        OutputView.printResult(blackjackGame.players)
    }

    private fun createPlayers(): Players {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Players(it) }
    }

    private fun playPlayersTurn(blackjackGame: BlackjackGame) {
        while (blackjackGame.isPlaying()) {
            val currentTurnPlayer = blackjackGame.findCurrentTurnPlayer()
            blackjackGame.askDrawToCurrentTurnPlayer(InputView.askDrawCard(currentTurnPlayer.name))
            OutputView.printCurrentPlayerCards(currentTurnPlayer)
        }
    }
}
