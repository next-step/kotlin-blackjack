package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView

class BlackjackGameController {
    fun run() {
        val deck = Deck.createOf()
        val players = createPlayers(deck)
    }

    private fun createPlayers(deck: Deck): Players {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Players(it) }
    }
}
