package blackjack.view

import blackjack.model.player.Player

interface Dashboard {
    fun printPlayerInitStatus(player: List<Player>)
}
