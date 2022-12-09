package blackjack.domain.member

class Players(
    val items: List<Player>
) {
    init {
        require(items.size >= MIN_SIZE) { "참가자는 최소 두명이상 이어야 해요." }
    }

    val size: Int
        get() = items.size

    fun toResultPlayers(dealer: Dealer): List<Player> {
        if (dealer.isOverBlackjackNumber()) {
            return items.map { WinPlayer.init(it) }
        }

        return items.map { it.toResultPlayer(dealer) }
    }

    companion object {
        private const val MIN_SIZE = 2
        fun List<Player>.toPlayers() = Players(this)
    }
}
