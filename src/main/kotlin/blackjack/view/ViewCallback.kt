package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

interface ViewCallback {
    fun showPlayerSet(players: Players)
    fun isMoreCard(player: Player) : Boolean
    fun showPlayerCards(player: Player)
    fun showGameResult(player: Player)

}
