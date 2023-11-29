package blackjack.domain

import blackjack.domain.state.Hit

class Players(private val players: List<Player>) : List<Player> by players {
    fun withHit(): List<Player> {
        return players.filter { it.canReceiveOneMoreCard() }
    }
}
