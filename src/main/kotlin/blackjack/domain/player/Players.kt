package blackjack.domain.player

class Players(playerNames: List<String>, dealer: Dealer) {
    val players: List<Player>

    init {
        players = playerNames
            .map { Player(it) }
            .toMutableList()

        players.add(dealer)
    }

    fun playersToPlay(): List<Player> {
        return players
            .filter { it !is Dealer }
            .filter { it.canMoreGame() }
    }
}
