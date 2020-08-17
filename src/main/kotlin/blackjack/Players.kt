package blackjack

class Players(private val playerNames: List<String>) {
    private val players: List<Player> =
        playerNames.map { Player(it, Cards((0..1).map { Card.getInstances() }.toMutableList())) }

    fun getPlayers() = players
}
