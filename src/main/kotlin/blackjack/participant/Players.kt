package blackjack.participant

data class Players(val player: List<Player>) {
    companion object {
        fun of(playerNames: List<String>): Players {
            return Players(playerNames.map { Player(PlayerName(it)) })
        }
    }
}
