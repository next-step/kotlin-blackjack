package model
class Players {
    private val players = mutableListOf<Player>()

    fun add(player: Player): Boolean {
        return players.add(player)
    }

    fun get(): List<Player> {
        return players
    }
}
