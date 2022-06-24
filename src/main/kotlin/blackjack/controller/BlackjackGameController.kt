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
        while (blackjackGame.isPlaying()) {
            // val player = blackjackGame.findCurrentPlayer()
        }

        OutputView.printAllInitCards(blackjackGame.players)
    }

    private fun createPlayers(): Players {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Players(it) }
    }
}
