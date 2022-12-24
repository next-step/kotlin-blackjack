package blackjack.domain.player

data class Players(
    private val players: List<Player>
): List<Player> by players {

    fun names(): List<String> = players.map { it.name }
}
