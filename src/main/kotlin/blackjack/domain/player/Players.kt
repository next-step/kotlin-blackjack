package blackjack.domain.player

import blackjack.domain.card.Deck

private const val GAME_START_DRAW_COUNT = 2

class Players(val players: List<Player>) {

    fun hitAtGameStart(deck: Deck) {
        repeat(GAME_START_DRAW_COUNT) {
            players.forEach { it.hit(deck) }
        }
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }

    companion object {

        fun from(names: List<PlayerName>): Players {
            val players = names.map { Player(it) }
            return Players(players)
        }
    }
}
