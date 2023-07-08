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
        println("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        players.forEach {
            ResultView.playerAndCards(it)
        }
    }

    fun drawMoreCard(player: Player): Boolean {
        if (!player.canDrawMoreCard()) {
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
        players.forEach {
            println("${it.name}카드: ${it.cards.joinToString(", ")} - 결과: ${it.calculateScore()}")
        }
    }
}
