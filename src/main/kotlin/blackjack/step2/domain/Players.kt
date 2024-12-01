package blackjack.step2.domain

class Players(private val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { "플레이어는 한 명 이상이어야 합니다." }
    }

    val names: List<String>
        get() = players.map { it.name }

    companion object {
        fun of(names: List<String>): Players {
            return Players(names.map { Player(it) })
        }
    }
}
