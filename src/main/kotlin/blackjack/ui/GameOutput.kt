package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

interface GameOutput {
    fun printDeckStatus(player: Player)
    fun printAllDeckStatus(players: Players)
}
