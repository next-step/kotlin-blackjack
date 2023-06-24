package blackjack.domain.player

class Players(val players: List<Player>) {
    fun getNames(): List<String> = players.map { it.name }
}
