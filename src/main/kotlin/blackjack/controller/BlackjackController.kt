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

    fun drawMoreCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        if (InputView.drawMoreCard()) {
            player.addCard(deck.drawCard())
            println("${player.name}카드: ${player.cards.joinToString(", ")}")
            return true
        }
        return false
    }
}
