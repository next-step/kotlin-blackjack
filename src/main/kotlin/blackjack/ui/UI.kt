package blackjack.ui

import blackjack.domain.Players

object UI {

    fun drawPlayers(players: Players) {
        println(players.list.joinToString(",") { it.name })
    }
}
