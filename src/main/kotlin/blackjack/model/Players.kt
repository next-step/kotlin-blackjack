package blackjack.model

class Players private constructor(private val players: List<Player>) : List<Player> by players {

    class Builder {
        private var players: MutableList<Player> = mutableListOf()

        fun players(players: MutableList<Player>): Builder {
            this.players = players
            return this
        }

        fun playerNames(playerNames: List<String>): Builder {
            this.players = playerNames.map { Player(it) }.toMutableList()
            return this
        }

        fun addPlayer(player: Player): Builder {
            this.players.add(player)
            return this
        }

        fun build() = Players(players.toList())
    }
}
