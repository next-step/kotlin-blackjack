package blackjack.domain

data class Players(private val players: List<Player>) {
    override fun toString(): String {
        return players.toString()
    }
}
