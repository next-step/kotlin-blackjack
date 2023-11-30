package blackjack.domain.player

data class Players(private val players: List<Player>) {
    var size: Int = players.size
        private set

    fun isAllFinished(): Boolean {
        return players.all { it.isFinished }
    }
}
