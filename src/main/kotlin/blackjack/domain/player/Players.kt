package blackjack.domain.player

data class Players(
    val allPlayers: List<Player>
) {
    companion object {
        fun from(names: PlayerNames) =
            names.value.map { name -> createPlayer(name) }.let(::Players)

        private fun createPlayer(name: PlayerName) =
            Player(name)
    }
}
