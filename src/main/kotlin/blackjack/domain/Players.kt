package blackjack.domain

class Players(val values: List<Player>) : List<Player> by values {
    fun deal(
        player: Player,
        deck: Deck,
    ) {
        find(player.name).receive(deck)
    }

    fun deal(
        player: Player,
        card: Card,
    ) {
        find(player.name).receive(Deck(listOf(card)))
    }

    fun isBust(name: String): Boolean {
        return find(name).isBust
    }

    fun findCardOf(name: String): Deck {
        return find(name).totalCards
    }

    fun find(playerName: String): Player {
        return values.find { it.same(playerName) } ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")
    }

    fun dealInitialCards(
        deck: Deck,
        initialCardCount: Int,
    ) {
        forEach { player ->
            player.receive(deck.popCards(initialCardCount))
        }
    }

    fun names(): List<String> {
        return values.map { it.name }
    }

    companion object {
        fun from(names: List<String>): Players {
            return Players(names.map { Player(EntrantName(it)) })
        }
    }
}
