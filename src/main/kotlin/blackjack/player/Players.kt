package blackjack.player

class Players(
    val players: List<Player>,
) {
    companion object {
        fun generatePlayersFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.from(name = it) })
    }
}
