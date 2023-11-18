package blackjack.domain.player

data class Players(
    val allPlayers: List<Player>,
) {
    var playerInTurn: Player = allPlayers[0]
        private set

    companion object {
        fun from(names: PlayerNames) =
            names.value.map { name -> createPlayer(name) }.let(::Players)

        private fun createPlayer(name: PlayerName) =
            Player(name)
    }
}
