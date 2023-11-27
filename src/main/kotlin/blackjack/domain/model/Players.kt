package blackjack.domain.model

class Players(val players: List<Player>) {
    fun findByName(playerName: PlayerName): Player? {
        return players.find { it.name == playerName }
    }
}
