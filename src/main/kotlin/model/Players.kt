package model
class Players {
    private val players = mutableListOf<Player>()

    fun create(names: Names) {
        names.values.forEach {
            add(Player(it))
        }
    }

    fun add(player: Player): Boolean {
        return players.add(player)
    }

    fun get(): List<Player> {
        return players
    }
}
