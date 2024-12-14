package blackjack.participant

data class Players(val players: List<Player>) {
    companion object {
        fun of(playerNames: List<String>): List<Player> {
            return playerNames.map { Player(PlayerName(it)) }
        }
    }
}
