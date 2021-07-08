package game

import model.AbstractPlayer
import model.PlayerName

class BlackJackGameStatusInfo(private val players: List<AbstractPlayer>) {

    fun playerNames(): List<PlayerName> {
        return players.map { it.name }
    }

    fun playerCardCount(): Int {
        return players.first().cardCount()
    }
}
