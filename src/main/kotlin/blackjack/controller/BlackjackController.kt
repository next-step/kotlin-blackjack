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

    fun printInitialCards(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        players.forEach {
            println("${it.name}카드: ${it.cards.joinToString(", ")}")
        }
    }
}
