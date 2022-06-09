package blackjack.domain.player

import blackjack.domain.game.TakeMorePlayerStrategy

class Players(playerNames: List<String>, takeMore: TakeMorePlayerStrategy, dealer: Dealer) {
    val players: List<Player>

    init {
        players = playerNames
            .map { Player(it, takeMore) }
            .toMutableList()

        players.add(dealer)
    }

    fun playersToPlay(): List<Player> {
        return players
            .filter { it !is Dealer }
            .filter { it.canMoreGame() }
    }
}
