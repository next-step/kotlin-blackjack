package blackjack.domain

import blackjack.domain.player.Player

class Players private constructor(private val players: List<Player>) {
    companion object {
        fun from(names: List<String>): Players = Players(names.map(Player.Companion::fromName))
    }
}
