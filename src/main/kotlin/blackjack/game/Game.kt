package blackjack.game

import blackjack.player.Player
import blackjack.player.Players

class Game {

    fun enrollPlayers(names: String): Players {
        val playerRequest = names.split(PLAYER_INPUT_DELIMITER).map { Player(it) }
        return Players.from(playerRequest)
    }

    companion object {
        private const val PLAYER_INPUT_DELIMITER = ","
    }

}
