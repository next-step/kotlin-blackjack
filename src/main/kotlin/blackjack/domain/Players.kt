package blackjack.domain

data class Players(val players: List<Player>) {

    companion object {
        fun init(names: List<String>): Players {
            return Players(players = names.map { Player(it) })
        }
    }
}
