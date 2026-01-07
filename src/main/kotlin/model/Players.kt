package model

class Players(
    private val players: List<Player>,
) : List<Player> by players {
    companion object {
        fun of(input: List<String>): Players = Players(input.map { Player(it) })
    }

    init {
        require(players.map { it.name }.toSet().size == players.size) { "참가자의 이름이 중복될 수 없습니다." }
    }
}
