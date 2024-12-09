package blackjack.core.player

class Players(val players: List<Player>) : List<Player> by players {
    constructor(names: Set<Name>) : this(initPlayers(names))

    fun play(action: (Player) -> Unit) {
        forEach(action)
    }

    companion object {
        fun initPlayers(names: Set<Name>): List<Player> {
            return names.map { Player(it) }
        }
    }
}
