package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

interface GameOutput {
    fun printDealInitialCards(players: Players)
    fun printDeckStatus(player: Player)
    fun printAllDeckStatus(players: Players)
}
