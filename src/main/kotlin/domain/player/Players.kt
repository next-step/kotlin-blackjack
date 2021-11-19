package domain.player

class Players(players: List<Player>) : List<Player> by players {
    private val players = players.toList()
    operator fun plus(player: Player): Players = Players(listOf(player) + players)
}
