package blackjack.domain

class Players(private val players: Set<Player>) : Set<Player> by players {
    val allEndGame
        get() = countOfPlayingState == NO_PLAYING_COUNT

    private val countOfPlayingState: Int
        get() = players.count { it.isPlaying }

    companion object {
        private const val NO_PLAYING_COUNT = 0
    }
}
