package domain

class Players(val players: List<Player>) {
    init {
        require(players.toSet().size == players.size) { "참가자의 이름이 중복될 수 없습니다." }
    }
}
