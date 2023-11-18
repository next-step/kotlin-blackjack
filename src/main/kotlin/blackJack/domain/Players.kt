package blackJack.domain

class Players(val players: List<Player>) {

    companion object {
        fun initBettings(names: List<String>): Players {
            val players = names.map { Player.initBetting(it) }
            return Players(players)
        }
    }
}
