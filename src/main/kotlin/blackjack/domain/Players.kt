package blackjack.domain

data class Players(private val players: List<Player>) {
    constructor(players: Players, dealer: Dealer) : this(listOf(dealer) + players.players)

    fun allPlayers(): List<Player> = players
}
