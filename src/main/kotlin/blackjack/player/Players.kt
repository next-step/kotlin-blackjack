package blackjack.player

class Players(val players: List<Player>) {

    companion object {
        fun of(playerNames: List<String>): Players {
            return Players(playerNames.map { Player(it) })
        }
    }

    fun names(): String {
        return this.players.joinToString(", ") { it.playerName }
    }

    fun size(): Int {
        return this.players.size
    }
}
