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

    operator fun get(index: Int): Player = roster[index]

    fun dealRoundOfCardsFrom(deck: Deck) {
        roster.forEach { it.drawFrom(deck) }
    }

    companion object {
        fun from(names: List<String>): Players = Players(names.map { Player(it) })

        fun from(vararg names: String): Players = from(names.toList())
    }
}
