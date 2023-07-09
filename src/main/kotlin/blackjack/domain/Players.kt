package blackjack.domain

class Players(
    val list: List<Player>
) {

    companion object {
        fun of(playerList: List<String>): Players {
            return playerList.map { Player(it) }.toPlayers()
        }
    }
}

private fun List<Player>.toPlayers() = Players(this)
