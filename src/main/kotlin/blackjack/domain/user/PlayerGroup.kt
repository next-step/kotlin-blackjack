package blackjack.domain.user

class PlayerGroup(val players: List<Player>) {
    companion object {
        fun create(playerNames: List<String>) = PlayerGroup(playerNames.map { playerName -> Player(playerName) }.toList())
    }
}

