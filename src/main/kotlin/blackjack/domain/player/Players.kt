package blackjack.domain.player

class Players {
    private val _players: MutableSet<Player> = mutableSetOf()

    val size: Int get() = _players.size
    fun add(player: Player) {
        _players.add(player)
    }

    fun contains(player: Player): Boolean {
        return _players.contains(player)
    }
}
