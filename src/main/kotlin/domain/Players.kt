package domain

class Players(val players: List<Player>) {
    companion object {
        fun of(input: List<String>): Players {
            return Players(input.map { Player(it) })
        }
    }

    init {
        require(players.toSet().size == players.size) { "참가자의 이름이 중복될 수 없습니다." }
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }
}
