package blackjack.model

object PlayersFactory {
    fun create(playerNames: List<String>): Players {
        val playerList = playerNames.map { Player(it) }

        return Players.Builder().players(playerList).build()
    }
}
