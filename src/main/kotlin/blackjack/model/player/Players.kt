package blackjack.model.player

class Players(
    val players: List<Player>
) {

    val first
        get(): Player {
            validateNotEmpty(players)

            return players[0]
        }

    init {
        validateNotEmpty(players)
    }

    fun findNext(currentPlayer: Player): Player {
        val nextIndex = players.indexOf(currentPlayer) + 1
        val maxIndex = players.size - 1

        if (nextIndex > maxIndex) {
            return players[nextIndex % players.size]
        }

        return players[nextIndex]
    }

    private fun validateNotEmpty(players: List<Player>) {
        require(players.isNotEmpty()) { "플레이어는 1명 이상이어야 합니다." }
    }
}
