package blackjack.domain.player

data class Players(
    private val players: List<Player>
) : List<Player> by players {

    fun names(): List<String> = players.map { it.name }

    fun notFinishedPlayers(): Players = players.filter { it.isNotFinished() }.toPlayers()
}

fun List<Player>.toPlayers(): Players =
    Players(this)
