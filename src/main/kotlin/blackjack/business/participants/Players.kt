package blackjack.business.participants

class Players private constructor(allPlayers: List<Player>) {

    private val _players: List<Player> = allPlayers

    val allPlayers: List<Player> = _players.toList()

    fun forEachPlayer(onPlayerAction: (Player) -> Unit) = allPlayers.forEach(onPlayerAction)

    init {
        require(allPlayers.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    companion object {
        fun from(playerNames: List<String>): Players {
            return Players(playerNames.map { Player.from(it) })
        }
    }
}
