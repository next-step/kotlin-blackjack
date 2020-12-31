package blackjack

import java.lang.IndexOutOfBoundsException

data class Players(private val players: MutableList<Player> = mutableListOf<Player>()) {

    fun findPlayer(nth: Int): Player {
        return players[nth]
    }

    fun totalNumberOfPlayers(): Int {
        return players.size
    }

    fun totalAmountOfCards(): Int {
        return players.map { it.amountOfCards() }.sum()
    }

    fun stateOfPlayerCard(nth: Int): String {
        if (nth < totalNumberOfPlayers()) {
            val playerName = findPlayer(nth)
            return playerName.stateCards()
        } else {
            throw IndexOutOfBoundsException("can't find player")
        }
    }
}
