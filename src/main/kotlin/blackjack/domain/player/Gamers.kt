package blackjack.domain.player

import blackjack.domain.card.Deck

private const val GAME_START_DRAW_COUNT = 2

class Gamers(private val players: List<Player>, val dealer: Dealer) {

    val gamers: List<Gamer> = listOf(dealer) + players

    fun hitAtGameStart(deck: Deck) {
        repeat(GAME_START_DRAW_COUNT) {
            dealer.hit(deck)
            players.forEach { it.hit(deck) }
        }
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }

    companion object {

        fun from(names: List<PlayerName>): Gamers {
            val players = names.map { Player(it) }
            return Gamers(players, Dealer())
        }
    }
}
