package blackjack.core

class Players(val players: List<Player>) : List<Player> by players {
    constructor(names: Set<Name>) : this(initPlayers(names))

    fun getNames(): String {
        return players.map { it.name }.joinToString(",", "", "")
    }

    companion object {
        fun initPlayers(names: Set<Name>): List<Player> {
            return names.map { Player(it) }
        }
    }
}
