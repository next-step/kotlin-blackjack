package blackjack.player

class Players(
    val players: List<Player>,
) {
    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.fromName(name = it) })
    }
}
