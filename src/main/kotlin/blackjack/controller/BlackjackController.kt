package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

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

    fun printInitialCards(players: List<Player>) {
        ResultView.initialCards(players)
    }

    fun drawMoreCard(player: Player): Boolean {
        if (!player.cards.canDrawMoreCard()) {
            return false
        }
        if (InputView.drawMoreCard(player.name)) {
            player.addCard(deck.drawCard())
            ResultView.playerAndCards(player)
            return true
        }
        return false
    }

    fun printResult(players: List<Player>) {
        ResultView.result(players)
    }
}
