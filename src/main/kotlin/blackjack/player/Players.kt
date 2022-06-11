package blackjack.player

class Players(private val players: List<Player>) {

    fun playerNames(): String {
        return this.players.joinToString(", ") { it.playerName }
    }
}
