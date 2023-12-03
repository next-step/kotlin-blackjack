package blackjack.domain

class Players(private val players: List<Player>) : List<Player> by players {
    fun withHit(): List<Player> {
        return players.filter { it.canReceiveOneMoreCard() }
    }

    fun getNames(): String {
        return players.joinToString(", ") { it.name }
    }
}
