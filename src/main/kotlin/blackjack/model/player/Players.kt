package blackjack.model.player

class Players private constructor(private val players: List<Player>) : List<Player> by players {

    class Builder {
        private var players: List<Player> = listOf()

        fun players(players: List<Player>): Builder {
            this.players = players
            return this
        }

        fun build() = Players(players.toList())
    }
}
