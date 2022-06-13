package blackjack.dummy

import blackjack.model.player.Player
import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players

class TestPlayerProvider(private val players: Players<Player.Guest>) : PlayerProvider {
    override fun createPlayers(previousPlayers: Players<Player.Guest>?) = this.players
}
