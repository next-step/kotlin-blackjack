package blackjack.model

class Players private constructor(private val players: List<Player>) {
    init {
        val duplicateNames =
            players.groupBy { it.name }
                .filter { it.value.size > 1 }
                .keys

        if (duplicateNames.isNotEmpty()) {
            throw IllegalArgumentException("중복된 플레이어 이름이 있습니다")
        }
    }

    fun getPlayers(): List<Player> = players.toList()

    fun getPlayerNames(): String = players.joinToString(",") { it.name }

    companion object {
        fun from(players: List<Player>): Players = Players(players)
    }
}
