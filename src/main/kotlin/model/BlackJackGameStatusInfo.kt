package model

class BlackJackGameStatusInfo(private val players: List<Player>) {

    fun playerNames(): List<PlayerName> {
        return players.map { it.name }
    }

    fun playerCardCount(): Int {
        return players.first().cardCount()
    }
}
