package blackjack.domain.player

class Players(playerNames: List<String>) {
    val players: List<Player>

    init {
        players = playerNames
            .map { Player(it) }
            .toMutableList()
    }

    fun playersToPlay(): List<Player> {
        return players.filter { it.canMoreGame() }
    }
}
