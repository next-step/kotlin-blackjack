package blackjack

class Players private constructor(private val values: List<Player>) {
    fun deal(
        player: Player,
        deck: List<Card>,
    ) {
        find(player).receive(deck)
    }

    fun findCardOf(player: Player): List<Card> {
        return find(player).totalCards
    }

    private fun find(player: Player): Player {
        return values.find { it.same(player) } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    val size: Int = values.size

    companion object {
        fun from(names: List<String>): Players {
            return Players(names.map { Player(Name(it)) })
        }
    }
}
