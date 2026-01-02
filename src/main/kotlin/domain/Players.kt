package domain

class Players(val players: List<Player>) {
    companion object {
        fun of(input: Map<String, Long>): Players {
            return Players(input.map { Player(name = it.key, bettingAmount = it.value) })
        }
    }

    init {
        require(players.map { it.name }.toSet().size == players.size) { "참가자의 이름이 중복될 수 없습니다." }
    }
}
