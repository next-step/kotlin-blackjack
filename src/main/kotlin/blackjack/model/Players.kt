package blackjack.model

class Players(players: List<Player>) {
    private val _players = players.toList()
    val players: List<Player>
        get() = _players.toList()

    init {
        val duplicateNames =
            players.groupBy { it.name }
                .filter { it.value.size > 1 }
                .keys

        if (duplicateNames.isNotEmpty()) throw IllegalArgumentException("중복된 플레이어 이름이 있습니다")
    }

    fun getPlayerNames(): String = _players.joinToString(",") { it.name }
}
