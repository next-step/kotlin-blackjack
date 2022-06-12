package blackjack.domain

class Players(
    players: List<Player> = emptyList()
) {
    private val _players = players.toMutableList()
    val players: List<Player> get() = _players.toList()

    fun namesAsText() = players.joinToString { it.name }
}

fun List<String>.toPlayers() = Players(this.map { Player(it) })
