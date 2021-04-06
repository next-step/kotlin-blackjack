package blackjack.domain

class Players(private val players: Set<Player>) : Set<Player> by players {
    val countOfPlayingState: Int
        get() = players.count { it.isPlayingState }
}
