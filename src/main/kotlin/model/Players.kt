package model
class Players {
    private val _players = mutableListOf<Player>()
    val values get() = _players.toList()

    fun create(names: Names) {
        names.values.forEach {
            add(Player(it))
        }
    }

    fun add(player: Player): Boolean {
        return _players.add(player)
    }
}
