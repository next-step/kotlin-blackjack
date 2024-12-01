package blackjack.domain

class Players(
    val roster: List<Player>,
) {
    init {
        require(roster.isNotEmpty()) { "플레이어 목록이 비어 있습니다" }

        val names = roster.map { it.name }
        require(names.distinct().size == roster.size) {
            "중복된 이름이 있습니다."
        }
    }

    companion object {
        fun from(names: List<String>): Players = Players(names.map { Player(it) })
    }
}
