package blackjack.domain

class Players private constructor(private val values: List<Player>) : List<Player> by values {
    fun deal(
        player: Player,
        deck: Deck,
    ) {
        player.receive(deck)
    }

    fun deal(
        player: Player,
        deck: Card,
    ) {
        player.receive(Deck(listOf(deck)))
    }

    fun findCardOf(name: String): Deck {
        return find(name).totalCards
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
