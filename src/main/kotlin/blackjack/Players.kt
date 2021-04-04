package blackjack

class Players(private val players: Set<Player>) {
    val countOfPlayingState: Int
        get() = players.count { it.isPlayingState }
}
