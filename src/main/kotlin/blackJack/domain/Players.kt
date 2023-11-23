package blackJack.domain

import blackJack.error.ErrorMessage
import java.util.*

class Players(val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { ErrorMessage.EMPTY_PLAYERS.message }
    }

    fun receiveInitialCards(cardDecks: Queue<Cards>) {
        players.forEach { it.receiveInitialCards(cardDecks.poll()) }
    }

    companion object {
        fun createPlayers(playerList: List<String>): Players {
            val players = playerList.map { Player.createPlayer(it) }
            return Players(players)
        }
    }
}
