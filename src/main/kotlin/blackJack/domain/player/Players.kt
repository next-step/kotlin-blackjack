package blackJack.domain.player

import blackJack.domain.card.Cards
import blackJack.error.ErrorMessage

class Players(val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { ErrorMessage.EMPTY_PLAYERS.message }
    }

    fun receiveInitialCards(initialCards: () -> Cards) {
        players.forEach { it.receiveInitialCards(initialCards.invoke()) }
    }

    companion object {
        fun createPlayers(playerList: List<String>): Players {
            val players = playerList.map { Player.createPlayer(it) }
            return Players(players)
        }
    }
}
