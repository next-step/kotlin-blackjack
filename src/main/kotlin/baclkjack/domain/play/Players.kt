package baclkjack.domain.play

class Players(private val players: List<Player>): List<Player> by players

fun List<Player>.toPlayers() = Players(this)
