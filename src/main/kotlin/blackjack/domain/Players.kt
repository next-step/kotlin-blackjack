package blackjack.domain

data class Players(private val players: MutableList<Player> = mutableListOf<Player>()) {

    fun findPlayer(nth: Int): Player {
        return players[nth]
    }

    fun totalNumberOfPlayers(): Int {
        return players.size
    }

    fun stateOfPlayerCard(): String {
        return players.joinToString("\n") { player -> "${player}카드: ${player.stateCards()}" }
    }

    override fun toString(): String {
        return players.joinToString()
    }
}
