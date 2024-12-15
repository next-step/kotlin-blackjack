package blackjack.domain

data class Players(private val players: List<Player>) {
    fun allPlayers(): List<Player> = players
}
