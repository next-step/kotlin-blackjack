package blackjack.domain.player


class Players(playerNames: List<String>) {
    val players: List<Player>

    init {
        players = playerNames.map {
            Player(it, mutableSetOf())
        }
    }
}
