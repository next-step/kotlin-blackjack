package domain

class Players(
    val players: List<Player>,
) {
    val size get() = players.size

    companion object {
        fun of(input: List<String>): Players = Players(input.map { Player(it) })
    }

    init {
        require(players.map { it.name }.toSet().size == players.size) { "참가자의 이름이 중복될 수 없습니다." }
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }
}
