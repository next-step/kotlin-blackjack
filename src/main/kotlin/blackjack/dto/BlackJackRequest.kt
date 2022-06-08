package blackjack.dto

import blackjack.domain.player.Player

class BlackJackRequest(val players: List<Player>) {
    companion object {

        fun of(players: List<String>): BlackJackRequest {
            val convertPlayers: MutableList<Player> = mutableListOf()
            for (p in players) convertPlayers.add(Player(p))
            return BlackJackRequest(convertPlayers)
        }
    }
}
