package blackjack.domain

data class Players(private val players: List<Player>) {
    fun allPlayers(): List<Player> = players

    fun dealer() = players[0] as Dealer // todo: 개선
}
