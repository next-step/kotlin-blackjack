package blackjack.domain

data class Players(private val players: List<Player>) : List<Player> by players
