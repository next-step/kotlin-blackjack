package blackjack.domain

class Players(private val players: Set<Player>) : Set<Player> by players {

    val allEndGame
        get() = countOfPlayingState == NO_PLAYING_COUNT

    private val countOfPlayingState: Int
        get() = players.count { it.isPlaying }

    fun findOneByStatusIsPlaying(): Player =
        players.find { it.isPlaying } ?: throw IllegalStateException("진행이 가능한 플레이어가 없습니다")

    companion object {
        private const val NO_PLAYING_COUNT = 0
    }
}
