package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView

class BlackjackController {
    private val deck = Deck()
    fun inputPlayers(): List<Player> {
        return InputView.players().map { Player(it) }
    }

    fun drawInitialCards(players: List<Player>) {
        players.forEach {
            it.addCard(deck.drawCard())
            it.addCard(deck.drawCard())
        }
    }
}
