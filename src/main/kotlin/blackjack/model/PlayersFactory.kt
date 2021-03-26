package blackjack.model

object PlayersFactory {
    fun create(playerNames: List<String>): Players {
        val players = playerNames.map { Player(it) }

        return Players.Builder().players(players).build()
    }
}
