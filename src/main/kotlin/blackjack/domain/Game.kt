package blackjack.domain

import blackjack.domain.dsl.buildGame

class Game(val players: Players, private val deck: Deck) {

    fun dealInitialCards() = players.forEach { player ->
        player.takeIf { it.isAddable() }?.addCardAll(deck.pick(INIT_TAKE_SIZE))
    }

    fun deal(player: Player, capacity: Int = 1) {
        player.takeIf { players.contains(player) && player.isAddable() }
            ?.addCardAll(deck.pick(capacity))
            ?: throw IllegalArgumentException("플레이어가 유효하지 않습니다. Player: $player")
    }

    companion object {
        const val INIT_TAKE_SIZE = 2
        const val THRESHOLD = 21

        fun from(requestNames: List<String>): Game =
            buildGame {
                players {
                    requestNames.forEach { name(it) }
                }
                deck {
                    Deck.create()
                }
            }
    }
}
