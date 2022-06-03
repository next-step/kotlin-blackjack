package blackjack.view.output

import blackjack.model.player.Player
import blackjack.model.player.Players

interface OutputView {
    fun printInitialMessage(players: Players)
    fun printCardsOfPlayer(player: Player, withScore: Boolean = false)
    fun printCardsOfPlayer(players: Players, withScore: Boolean = false) {
        players.forEach { this.printCardsOfPlayer(it, withScore) }
    }
}
