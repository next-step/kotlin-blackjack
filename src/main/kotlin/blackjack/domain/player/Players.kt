package blackjack.domain.player

data class Players(
    private val players: List<Player>
): List<Player> by players {

    fun names(): List<String> = players.map { it.name }

    fun notFinishedPlayers(): List<Player> = players.filter { it.isNotFinished() }
}
