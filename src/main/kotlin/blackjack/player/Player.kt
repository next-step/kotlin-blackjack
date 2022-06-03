package blackjack.player

class Players private constructor(
    val players: List<Player>
) {
    companion object {
        fun from(playerRequest: List<Player>): Players {
            return Players(playerRequest)
        }
    }
}

data class Player(
    val name: String
)
