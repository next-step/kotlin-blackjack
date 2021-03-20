package blackjack.model

class Players private constructor(private val players: List<Player>) : List<Player> by players {
    override fun toString(): String {
        return players.joinToString(", ") { "$it" }
    }

    class Builder {
        private var players: List<Player> = listOf()

        fun players(players: List<Player>): Builder {
            this.players = players
            return this
        }

        fun playerNames(playerNames: List<String>): Builder {
            this.players = playerNames.map { Player(it) }
            return this
        }

        fun build() = Players(players.toList())
    }
}
