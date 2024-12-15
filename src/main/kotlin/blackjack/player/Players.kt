package blackjack.player

class Players(
    val players: List<Player>,
) {
    fun add(newPlayers: Players) = Players(players = newPlayers.players.plus(this.players))

    fun getRemainedPlayers() =
        players.filterNot { it.isBust() }
    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.ready(name = it) })
    }
}
