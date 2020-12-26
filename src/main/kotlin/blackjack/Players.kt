package blackjack

import java.lang.IndexOutOfBoundsException

data class Players(private val player: MutableList<Player> = mutableListOf<Player>()) {

    fun findPlayer(nth: Int): Player {
        return player[nth]
    }

    fun totalNumberOfPlayers(): Int {
        return player.size
    }

    fun stateOfPlayerCard(nth:Int): String {
        if (nth < totalNumberOfPlayers())  {
            val playerName = findPlayer(nth)
            return playerName.stateCards()
        }
        else{
            throw IndexOutOfBoundsException("can't find player")
        }
    }


}