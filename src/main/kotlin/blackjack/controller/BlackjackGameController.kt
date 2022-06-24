package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGameController {
    fun run() {
        val deck = Deck.createOf()
        val players = createPlayers()
        players.drawInitCards(deck)
        OutputView.printAllInitCards(players)
    }

    private fun createPlayers(): Players {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Players(it) }
    }
}
