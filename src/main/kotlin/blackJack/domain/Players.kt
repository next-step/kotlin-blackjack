package blackJack.domain

import blackJack.error.ErrorMessage

class Players(val players: List<Player>) {

    init {
        require(players.isNotEmpty()) { ErrorMessage.EMPTY_PLAYERS.message }
    }

    companion object {
        fun initBettings(names: List<String>): Players {
            val players = names.map { Player.initBetting(it) }
            return Players(players)
        }
    }
}
