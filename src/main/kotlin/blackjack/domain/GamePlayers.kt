package blackjack.domain

class GamePlayers(
    val players: List<Player>,
) : Collection<Player> by players {
    fun calculateScore() {
        players.forEach { player ->
            player.calculateCardScore()
        }
    }

    companion object {
        fun from(names: List<String>): GamePlayers = GamePlayers(names.map { Player(it) })
    }
}
