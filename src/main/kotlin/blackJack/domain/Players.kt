package blackJack.domain

import blackJack.error.ErrorMessage

class Players(val players: List<Player>) {

    init {
        require(players.isNotEmpty()) { ErrorMessage.EMPTY_PLAYERS.message }
    }

//    fun playGame(dealer: Dealer): Players = Players(players.map { it.playGame(dealer) })

    companion object {
        fun createPlayers(playerList: List<String>, dealer: Dealer): Players {
            val players = playerList.map { Player.createPlayer(it, dealer) }
            return Players(players)
        }
    }
}
