package blackjack.model.player

import blackjack.model.Rule

class Players private constructor(private val players: List<Player>) : List<Player> by players {
    fun countWin(opponent: Player, rule: Rule): Int {
        return players.filter { it.isWin(opponent, rule) }.count()
    }

    fun countLose(opponent: Player, rule: Rule): Int {
        return players.filter { it.isLose(opponent, rule) }.count()
    }

    class Builder {
        private var players: List<Player> = listOf()

        fun players(players: List<Player>): Builder {
            this.players = players
            return this
        }

        fun build() = Players(players.toList())
    }
}
