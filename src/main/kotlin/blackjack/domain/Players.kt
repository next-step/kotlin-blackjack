package blackjack.domain

class Players(private val players: List<Player>) {
    fun names(): List<String> = players.map { it.toString() }

    fun all() = players

    companion object {
        fun of(names: List<String>): Players = Players(List(names.size) { Player(names[it]).start() })
    }
}
