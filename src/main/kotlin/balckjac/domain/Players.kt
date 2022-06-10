package balckjac.domain

class Players(
    players: List<Player> = emptyList()
) {
    private val _payers = players.toMutableList()
    val players: List<Player> get() = _payers.toList()

    fun namesAsText() = players.joinToString { it.name }
}

fun List<String>.toPlayers() = Players(this.map { Player(it) })
