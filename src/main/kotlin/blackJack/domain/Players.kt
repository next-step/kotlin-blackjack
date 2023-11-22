package blackJack.domain

import blackJack.error.ErrorMessage

class Players(val players: List<Player>) {

    init {
        require(players.isNotEmpty()) { ErrorMessage.EMPTY_PLAYERS.message }
    }

//    fun playGame(): Players {
//        players.map { it.playGame() }
//    }

    companion object {
        fun createPlayers(playerList: List<String>, dealer: Dealer): Players {
            val players = playerList.map { Player.createPlayer(it, dealer) }
            return Players(players)
        }
    }
}
