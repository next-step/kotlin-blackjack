package blackJack.domain.player

import blackJack.utils.StringUtils

class Players(private val players: List<Player>) : List<Player> by players {

    companion object {
        fun of(names : String): Players {
            val value = StringUtils.splitString(names).map { Player.of(it) }
            return Players(value)
        }
    }
}
