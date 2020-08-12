package blackjack

class Players(private val playerNames: List<String>) {
    val players: List<Player> = playerNames.map { Player(it) }
}
