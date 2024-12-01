package blackjack.domain

class Players private constructor(private val values: List<Player>) : List<Player> by values {
    fun deal(
        player: Player,
        deck: Deck,
    ) {
        find(player).receive(deck)
    }

    fun deal(
        player: Player,
        deck: Card,
    ) {
        find(player).receive(Deck(listOf(deck)))
    }

    fun findCardOf(player: Player): Deck {
        return find(player).totalCards
    }

    private fun find(player: Player): Player {
        return values.find { it.same(player) } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    fun find(playerName: String): Player {
        return values.find { it.same(playerName) } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    fun scoreOf(name: String): Int {
        return find(name).score()
    }

    fun isBust(name: String): Boolean {
        return find(name).isBust
    }

    companion object {
        fun from(names: List<String>): Players {
            return Players(names.map { Player(Name(it)) })
        }
    }
}
