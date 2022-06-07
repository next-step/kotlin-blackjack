package blackjack.dummy

import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players

class TestPlayerProvider(private val players: Players) : PlayerProvider {
    override fun createPlayers() = this.players
}
