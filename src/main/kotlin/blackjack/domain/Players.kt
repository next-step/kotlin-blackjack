package blackjack.domain

data class Players(val players: List<Player>) : Collection<Player> by players {
    fun getPlayerNames(): List<String> {
        return players.map { it.name }
    }

    companion object {
        private const val DELIMITER = ","

        fun create(playerNames: String): Players {
            val players = playerNames.split(DELIMITER).map { playerName -> Player(playerName) }
            return Players(players)
        }
    }
}
